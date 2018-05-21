package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class BookingDayServiceImpl implements BookingDayService {
    @Override
    public Optional<BookingDay> getBookingDay(final Calendar date) {
        return Optional.ofNullable(BookingDay.builder().day(Calendar.getInstance()).build());
    }
}
