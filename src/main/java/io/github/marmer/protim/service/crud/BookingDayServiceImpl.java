package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class BookingDayServiceImpl implements BookingDayService {
    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private Converter<BookingDayDBO, BookingDay> toBookingDayConverter;

    @Override
    public Optional<BookingDay> getBookingDay(final Calendar date) {
        final BookingDayDBO bookingDay = bookingDayRepository.findByDay(date);
        return Optional.ofNullable(toBookingDayConverter.convert(bookingDay));
    }
}
