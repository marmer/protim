package io.github.marmer.protim.api.dto.testdata;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.protim.api.controller.booking.BookingDTO;

public class BookingDTOTestdata {
    public static BookingDTO newBookingDTO() {
        final EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(42)
                .build();
        return builder.nextObject(BookingDTO.class);
    }
}
