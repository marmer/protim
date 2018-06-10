package io.github.marmer.protim.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class BookingDayIdsDTO {
    private List<Long> ids;
}
