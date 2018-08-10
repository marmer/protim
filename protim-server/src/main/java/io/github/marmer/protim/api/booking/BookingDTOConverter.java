package io.github.marmer.protim.api.booking;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.booking.Booking;
import org.springframework.stereotype.Service;

@Service("converterBookingToBookingDTO")
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
