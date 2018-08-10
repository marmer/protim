package io.github.marmer.protim.api.booking;

import io.github.marmer.protim.service.Converter;
import io.github.marmer.protim.service.booking.BookingDay;
import org.springframework.stereotype.Service;

@Service("converterBookingDayToBookingDayDTO")
public class BookingDayDTOConverter implements Converter<BookingDay, BookingDayDTO> {

    @Override
    public BookingDayDTO convert(final BookingDay source) {
        return new BookingDayDTO().setDay(source.getDay());
    }
}
