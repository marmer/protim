package io.github.marmer.protim.persistence.relational.dbo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "booking_days")
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
    @Version
    private Long version;

    public void addBookings(final BookingDBO bookingDbo) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        bookings.add(bookingDbo);
    }
}
