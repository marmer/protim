package io.github.marmer.protim.api.converter;

import io.github.marmer.protim.api.dto.BookingDTO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingDTOConverter implements Converter<Booking, BookingDTO> {

    @Override
    public BookingDTO convert(final Booking source) {
        return source == null ?
                null :
                new BookingDTO()
                        .setStartTime(source.getStartTime())
                        .setDuration(source.getDuration())
                        .setDescription(source.getDescription())
                        .setNotes(source.getNotes())
                        .setTicket(source.getTicket());
    }
}
