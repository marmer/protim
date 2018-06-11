package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDBO;
import io.github.marmer.protim.service.model.Booking;

public class BookingConverter implements Converter<BookingDBO, Booking> {
    @Override
    public Booking convert(final BookingDBO source) {
        return null;
    }
}
