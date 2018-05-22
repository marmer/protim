package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.service.model.BookingDay;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingDayDBOToBookingDayConverter implements Converter<BookingDayDBO, BookingDay> {
    @Override
    public BookingDay convert(final BookingDayDBO source) {
        return source == null ?
                null :
                BookingDay.builder()
                        .day(LocalDate.from(source.getDay().toInstant()))
                        .id(source.getId())
                        .build();
    }
}
