package io.github.marmer.protim.persistence.dbo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "booking_day")
@Accessors(chain = true)
public class BookingDayDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Basic
    @Column(unique = true, nullable = false)
    private LocalDate day;
    @JoinColumn(name = "day")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDBO> bookings;
}
