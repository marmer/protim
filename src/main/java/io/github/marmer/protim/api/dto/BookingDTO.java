package io.github.marmer.protim.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@Accessors(chain = true)
public class BookingDTO {
    @JsonFormat(pattern = "mm:HH")
    private LocalTime startTime;
    @JsonFormat(pattern = "mm:HH")
    private LocalTime duration;
    private String description;
    private String notes;
    private String ticket;
}
