package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.service.crud.BookingDayService;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayController {

    private final BookingDayService bookingDayService;
    private final Converter<BookingDay, BookingDayDTO> toBookingDayDTOConverter;

    @GetMapping("/{day}")
    public ResponseEntity<BookingDayDTO> getDay(@PathVariable("day") final String day) {


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Date date;
        try {
            date = sdf.parse(day);
        } catch (final ParseException e) {
            return ResponseEntity.notFound().build();
        }
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        final Optional<BookingDay> bookingDay = bookingDayService.getBookingDay(cal);
        return bookingDay
                .map(bookingDay1 -> ResponseEntity.ok(toBookingDayDTOConverter.convert(bookingDay1)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
