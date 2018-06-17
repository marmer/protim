package io.github.marmer.protim.persistence.relational.converter;

import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

@Service
public class BookingDayConverterFromDBO implements Converter<BookingDayDBO, BookingDay> {
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
