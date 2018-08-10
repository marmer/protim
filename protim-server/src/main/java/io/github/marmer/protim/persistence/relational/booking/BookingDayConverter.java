package io.github.marmer.protim.persistence.relational.booking;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.booking.BookingDay;
import org.springframework.stereotype.Service;

@Service("converterBookingDayDboToBookingDay")
public class BookingDayConverter implements Converter<BookingDayDBO, BookingDay> {
    @Override
    public BookingDay convert(final BookingDayDBO source) {
        if (source == null) {
            return null;
        }
        return BookingDay.builder()
                .day(source.getDay())
                        .id(source.getId())
                        .build();
    }
}
