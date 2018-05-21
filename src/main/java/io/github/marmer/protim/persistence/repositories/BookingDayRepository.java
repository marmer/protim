package io.github.marmer.protim.persistence.repositories;


import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
}
