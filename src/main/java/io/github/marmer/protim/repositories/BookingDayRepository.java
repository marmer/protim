package io.github.marmer.protim.repositories;


import io.github.marmer.protim.model.dbo.BookingDayDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDayRepository extends JpaRepository<BookingDayDBO, Long> {
}
