package io.github.marmer.protim.persistence.relational.booking;

import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingConverterFromDBO implements Converter<BookingDBO, Booking> {
    @Override
    public Booking convert(final BookingDBO source) {
        return source == null ? null : Booking.builder()
                .startTime(source.getStartTime())
                .duration(source.getDuration())
                .description(source.getDescription())
                .notes(source.getNotes())
                .ticket(source.getTicket())
                .build();
    }
}
