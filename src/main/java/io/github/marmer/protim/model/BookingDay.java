package io.github.marmer.protim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Entity(name = "booking_day")
public class BookingDay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Calendar localDate;

    @JoinColumn(name = "day")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
}
