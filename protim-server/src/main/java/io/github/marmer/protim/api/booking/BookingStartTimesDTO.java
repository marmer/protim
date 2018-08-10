package io.github.marmer.protim.api.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class BookingStartTimesDTO {
    @JsonFormat(pattern = "HH:mm")
    private List<LocalTime> startTimes;
}
