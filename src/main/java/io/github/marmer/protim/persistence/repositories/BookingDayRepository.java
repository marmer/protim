package io.github.marmer.protim.persistence.repositories;


import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
    BookingDayDBO findByDay(Calendar day);
}
