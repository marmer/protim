package io.github.marmer.protim.service.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalTime;

@Value
@Builder
@Wither
class Booking {
    private LocalTime startTime;
    private LocalTime duration;
    private String description;
    private String notes;
    private String ticket;
}
