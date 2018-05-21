package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.service.crud.BookingDayService;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Optional;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayController {

    private final BookingDayService bookingDayService;
    private final Converter<BookingDay, BookingDayDTO> toBookingDayDTOConverter;

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}")
    public ResponseEntity<BookingDayDTO> getDay(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final Calendar day) {
        final Optional<BookingDay> bookingDay = bookingDayService.getBookingDay(day);
        return bookingDay
                .map(bookingDay1 -> ResponseEntity.ok(toBookingDayDTOConverter.convert(bookingDay1)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
