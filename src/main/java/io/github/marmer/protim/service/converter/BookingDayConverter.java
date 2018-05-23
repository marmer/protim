package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

@Service
public class BookingDayConverter implements Converter<BookingDayDBO, BookingDay> {
    @Override
    public BookingDay convert(final BookingDayDBO source) {
        if (source == null) {
            return null;
        }
        return BookingDay.builder()
                .day(source.getDay())
                        .id(source.getId())
                        .build();
    }
}
