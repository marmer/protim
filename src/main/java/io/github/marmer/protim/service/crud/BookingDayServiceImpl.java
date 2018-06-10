package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayServiceImpl implements BookingDayService {
    private final BookingDayRepository bookingDayRepository;
    private final Converter<BookingDayDBO, BookingDay> toBookingDayConverter;

    @Override
    public Optional<BookingDay> getBookingDay(final LocalDate date) {
        final BookingDayDBO bookingDay = bookingDayRepository.findByDay(date);
        return Optional.ofNullable(toBookingDayConverter.convert(bookingDay));
    }

    @Override
    public List<Long> getEnttyIDsForDay(final LocalDate day) {
        return bookingDayRepository.findBookingIdsForDay(day);
    }
}
