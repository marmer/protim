package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/day")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayController {
    private final BookingDayRepository bookingDayRepository;

    @GetMapping
    public BookingDayDBO getDay() {
        final List<BookingDayDBO> all = bookingDayRepository.findAll();
        return all.get(0);
    }
}