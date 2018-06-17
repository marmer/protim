package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.relational.repositories.BookingDayRepository;
import io.github.marmer.protim.service.converter.Converter;
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
public class BookingsServiceImpl implements BookingsService {
    private final BookingDayRepository bookingDayRepository;
    private final Converter<BookingDayDBO, BookingDay> bookingDayDTOConverter;
    private final Converter<BookingDBO, Booking> bookingDTOConverter;

    @Override
    public Optional<BookingDay> getBookingDay(final LocalDate date) {
        final Optional<BookingDayDBO> bookingDay = bookingDayRepository.findFirstByDayIs(date);
        return Optional.of(
                bookingDay
                        .map(bookingDayDTOConverter::convert)
                        .orElseGet(() -> BookingDay.builder().day(date).build()));
    }

    @Override
    public List<LocalTime> getBookingStartTimesForDay(final LocalDate day) {
        return bookingDayRepository.findBookingStartTimesForDay(day);
    }

    @Override
    public Optional<Booking> getBookingAtDayForTime(final LocalDate day, final LocalTime startTime) {
        final Optional<BookingDBO> booking = bookingDayRepository.findBookingByStartTimeForDay(day, startTime);
        return booking.map(bookingDTOConverter::convert);
    }

    @Override
    public void setBookingAtDay(final LocalDate day, final Booking booking) {
        // TODO: marmer 15.06.2018 implement me
    }
}
