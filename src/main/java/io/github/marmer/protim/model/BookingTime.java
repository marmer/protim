package io.github.marmer.protim.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class BookingTime {
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    private Calendar startTime;

    @Column(name = "length_in_ms")
    private Long lengthInMs;
}
