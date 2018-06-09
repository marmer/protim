package io.github.marmer.protim.service.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
@Wither
public class BookingDay {
    private Long id;
    private LocalDate day;
    private List<Booking> booking;
}
