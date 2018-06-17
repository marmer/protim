package io.github.marmer.protim.persistence.relational.dbo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "bookings")
@Accessors(chain = true)
public class BookingDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    @Basic
    private LocalTime startTime;
    @Column
    @Basic
    private LocalTime duration;
    @Column
    private String description;
    @Column
    private String notes;
    @Column
    private String ticket;
    @Version
    private Long version;
}
