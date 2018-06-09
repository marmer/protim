package io.github.marmer.protim.persistence.repositories;


import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
    BookingDayDBO findByDay(LocalDate day);

    List<Long> findEntryIdsFor(LocalDate day);
}
