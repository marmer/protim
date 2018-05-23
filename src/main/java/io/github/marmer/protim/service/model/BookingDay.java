package io.github.marmer.protim.service.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingDay {
    private Long id;
    private LocalDate day;
}
