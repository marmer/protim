package io.github.marmer.protim.api.dto.testdata;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.protim.service.model.Booking;

public class BookingTestdata {
    public static Booking newBooking() {
        final EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(42)
                .build();
        return builder.nextObject(Booking.class);
    }
}
