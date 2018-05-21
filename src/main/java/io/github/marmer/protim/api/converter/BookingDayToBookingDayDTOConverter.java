package io.github.marmer.protim.api.converter;

import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingDayToBookingDayDTOConverter implements Converter<BookingDay, BookingDayDTO> {

    @Override
    public Optional<BookingDayDTO> convert(final BookingDay source) {
        return Optional.ofNullable(new BookingDayDTO().setDay("8765-43-21"));
    }
}
