package io.github.marmer.protim.persistence.dbo;

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
    @Column(unique = true, nullable = false)
    private Calendar day;
}
