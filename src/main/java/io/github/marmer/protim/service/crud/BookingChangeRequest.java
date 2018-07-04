package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.service.model.Booking;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDate;
import java.time.LocalTime;


@Value
@Wither
@Builder
@RequiredArgsConstructor
public class BookingChangeRequest {
    private final LocalDate day;
    private final LocalTime startTime;
    private final Booking booking;
}
