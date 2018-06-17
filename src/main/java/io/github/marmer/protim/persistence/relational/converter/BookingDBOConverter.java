package io.github.marmer.protim.persistence.relational.converter;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingDBOConverter implements Converter<Booking, BookingDBO> {
    @Override
    public BookingDBO convert(final Booking source) {
        return source == null ? null : new BookingDBO()
                .setStartTime(source.getStartTime())
                .setDuration(source.getDuration())
                .setDescription(source.getDescription())
                .setNotes(source.getNotes())
                .setTicket(source.getTicket());
    }
}
