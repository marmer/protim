package io.github.marmer.protim.persistence.repositories;


import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
    BookingDayDBO findByDay(LocalDate day);

    @Query("SELECT b.id FROM BookingDayDBO d JOIN FETCH BookingDBO b WHERE d.day = ?1")
    List<Long> findEntryIdsForDay(LocalDate day);
}
