package io.github.marmer.protim.api.converter;

import io.github.marmer.protim.api.controller.booking.BookingDayDTO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

@Service
public class BookingDayDTOConverter implements Converter<BookingDay, BookingDayDTO> {

    @Override
    public BookingDayDTO convert(final BookingDay source) {
        return new BookingDayDTO().setDay(source.getDay());
    }
}
