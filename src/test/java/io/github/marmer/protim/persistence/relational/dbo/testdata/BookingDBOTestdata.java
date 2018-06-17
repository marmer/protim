package io.github.marmer.protim.persistence.relational.dbo.testdata;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;

public class BookingDBOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(42)
                .build();

    public static BookingDBO newBookingDBO() {
        return builder.nextObject(BookingDBO.class);
    }
}
