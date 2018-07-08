package io.github.marmer.protim.persistence.relational.booking;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class BookingDBOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(42)
                .build();

    public static BookingDBO newBookingDBO() {
        return builder.nextObject(BookingDBO.class);
    }
}
