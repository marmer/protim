package io.github.marmer.protim.persistence.relational.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
    Optional<BookingDayDBO> findFirstByDay(LocalDate day);

    @Query("SELECT b.startTime FROM BookingDayDBO d JOIN d.bookings b WHERE d.day = ?1 ORDER BY b.startTime")
    List<LocalTime> findBookingStartTimesForDay(LocalDate day);

    @Query("SELECT b FROM BookingDayDBO d JOIN d.bookings b WHERE d.day = ?1 AND b.startTime = ?2 ORDER BY b.startTime")
    Optional<BookingDBO> findBookingByStartTimeForDay(LocalDate day, LocalTime startTime);
}
