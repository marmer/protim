package io.github.marmer.protim.persistence.relational.crud;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.crud.BookingsCrudService;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingsCrudServiceRelational implements BookingsCrudService {
    private final BookingDayRepository bookingDayRepository;
    private final Converter<BookingDayDBO, BookingDay> bookingDayConverter;
    private final Converter<BookingDBO, Booking> bookingConverter;
    private final Converter<Booking, BookingDBO> bookingDBOConverter;

    @Override
    public Optional<BookingDay> getBookingDay(final LocalDate date) {
        final Optional<BookingDayDBO> bookingDay = bookingDayRepository.findFirstByDay(date);
        return Optional.of(
                bookingDay
                        .map(bookingDayConverter::convert)
                        .orElseGet(() -> BookingDay.builder().day(date).build()));
    }

    @Override
    public List<LocalTime> getBookingStartTimesForDay(final LocalDate day) {
        return bookingDayRepository.findBookingStartTimesForDay(day);
    }

    @Override
    public Optional<Booking> getBookingAtDayForTime(final LocalDate day, final LocalTime startTime) {
        final Optional<BookingDBO> booking = bookingDayRepository.findBookingByStartTimeForDay(day, startTime);
        return booking.map(bookingConverter::convert);
    }

    @Override
    public void setBookingAtDay(final LocalDate day, final Booking booking) {
        final BookingDayDBO bookingDayDbo = bookingDayRepository.findFirstByDay(day).orElse(new BookingDayDBO().setDay(day));
        final BookingDBO bookingDbo = bookingDBOConverter.convert(booking);

        bookingDayDbo.addBookings(bookingDbo);
        bookingDayRepository.save(bookingDayDbo);
    }
}
