package io.github.marmer.protim.api.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class BookingDayDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate day;
}
