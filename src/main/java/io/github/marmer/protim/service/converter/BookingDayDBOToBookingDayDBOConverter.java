package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.service.model.BookingDay;

public class BookingDayDBOToBookingDayDBOConverter implements Converter<BookingDayDBO, BookingDay> {
    @Override
    public BookingDay convert(final BookingDayDBO source) {
        return null;
    }
}
