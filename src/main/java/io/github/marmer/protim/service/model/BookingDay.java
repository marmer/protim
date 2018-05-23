package io.github.marmer.protim.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingDay {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    private LocalDate day;
}
