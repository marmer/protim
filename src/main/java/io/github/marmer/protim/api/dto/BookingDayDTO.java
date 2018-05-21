package io.github.marmer.protim.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Calendar;

@Data
@Accessors(chain = true)
public class BookingDayDTO {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    private Calendar day;
}
