package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.api.dto.BookingDTO;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.api.dto.BookingStartTimesDTO;
import io.github.marmer.protim.service.crud.BookingDayService;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayController {

    private final BookingDayService bookingDayService;
    private final Converter<BookingDay, BookingDayDTO> toBookingDayDTOConverter;
    private final Converter<Booking, BookingDTO> toBookingDTOConverter;

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}")
    public ResponseEntity<BookingDayDTO> getDay(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final Optional<BookingDay> bookingDay = bookingDayService.getBookingDay(day);
        return bookingDay
                .map(bookingDay1 -> ResponseEntity.ok(toBookingDayDTOConverter.convert(bookingDay1)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings")
    public ResponseEntity<BookingStartTimesDTO> getBookingIds(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final List<LocalTime> startTimes = bookingDayService.getBookingStartTimesForDay(day);
        return ResponseEntity.ok(new BookingStartTimesDTO().setStartTimes(startTimes));
    }

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings/{startTime}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day,
                                                 @PathVariable("startTime") @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME) final LocalTime startTime) {
        final Optional<Booking> booking = bookingDayService.getBookingForTime(day, startTime);
        return ResponseEntity.ok(toBookingDTOConverter.convert(booking.get()));
    }
}
