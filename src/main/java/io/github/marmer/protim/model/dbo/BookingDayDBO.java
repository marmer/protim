package io.github.marmer.protim.model.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity(name = "booking_day")
@Accessors(chain = true)
public class BookingDayDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+01:00")
    private Calendar day;
}
