package io.github.marmer.protim.api.booking;

import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingConverter implements Converter<BookingDTO, Booking> {
    @Override
    public Booking convert(final BookingDTO source) {
        return source == null ? null : Booking.builder()
                .startTime(source.getStartTime())
                .duration(source.getDuration())
                .description(source.getDescription())
                .notes(source.getNotes())
                .ticket(source.getTicket())
                .build();
    }
}
