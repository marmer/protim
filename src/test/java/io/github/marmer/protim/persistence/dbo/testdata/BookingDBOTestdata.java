package io.github.marmer.protim.persistence.dbo.testdata;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.protim.persistence.dbo.BookingDBO;

public class BookingDBOTestdata {
    public static BookingDBO newBookingDBO() {
        final EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(42)
                .build();
        return builder.nextObject(BookingDBO.class);
    }
}
