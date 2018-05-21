package io.github.marmer.protim.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookingDayDTO {
    private Long id;
    private String day;
}
