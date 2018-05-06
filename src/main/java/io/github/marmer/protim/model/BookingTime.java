package io.github.marmer.protim.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Data
@Accessors(chain = true)
@Embeddable
public class BookingTime {
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    private Calendar startTime;

    @Column(name = "length_in_ms")
    private Long lengthInMs;
}
