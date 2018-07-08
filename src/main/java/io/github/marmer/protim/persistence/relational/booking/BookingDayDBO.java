package io.github.marmer.protim.persistence.relational.booking;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "booking_days")
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"id", "version"})
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
    private List<BookingDBO> bookings = new ArrayList<>();
    @Version
    private Long version;

    public void addBookings(final BookingDBO bookingDbo) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        bookings.add(bookingDbo);
    }
}
