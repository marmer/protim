package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.dto.BookingDTO;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.api.dto.BookingStartTimesDTO;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.crud.BookingChangeRequest;
import io.github.marmer.protim.service.crud.BookingsCrudService;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingDayController {

    private final BookingsCrudService bookingsCrudService;
    private final Converter<BookingDay, BookingDayDTO> bookingDayDTOConverter;
    private final Converter<Booking, BookingDTO> bookingDTOConverter;
    private final Converter<BookingDTO, Booking> bookingConverter;

    @PutMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings/")
    @ResponseStatus(HttpStatus.CREATED)
    public void putBooking(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day,
                           @RequestBody final BookingDTO bookingDto) {
        putBooking(day, null, bookingDto);
    }

    @DeleteMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings/{oldStartTime:\\d{2}:\\d{2}}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day,
                              @PathVariable("oldStartTime") @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME) final LocalTime oldStartTime) {
        bookingsCrudService.delete(bookingChangeRequestWith().day(day).startTime(oldStartTime).build());
    }

    @PutMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings/{oldStartTime:\\d{2}:\\d{2}}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putBooking(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day,
                           @PathVariable("oldStartTime") @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME) final LocalTime oldStartTime,
                           @RequestBody final BookingDTO bookingDto) {
        final Booking booking = bookingConverter.convert(bookingDto);
        bookingsCrudService.setBookingAtDay(bookingChangeRequestWith().day(day).booking(booking).startTime(oldStartTime).build());
    }


    private BookingChangeRequest.BookingChangeRequestBuilder bookingChangeRequestWith() {
        return BookingChangeRequest.builder();
    }

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}")
    public ResponseEntity<BookingDayDTO> getDay(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final Optional<BookingDay> bookingDay = bookingsCrudService.getBookingDay(day);
        return bookingDay
                .map(bookingDayDTOConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings")
    public ResponseEntity<BookingStartTimesDTO> getBookingIds(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final List<LocalTime> startTimes = bookingsCrudService.getBookingStartTimesForDay(day);
        return ResponseEntity.ok(new BookingStartTimesDTO().setStartTimes(startTimes));
    }

    @GetMapping("/{day:\\d{4}-\\d{2}-\\d{2}}/bookings/{startTime}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) final LocalDate day,
                                                 @PathVariable("startTime") @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME) final LocalTime startTime) {
        final Optional<Booking> booking = bookingsCrudService.getBookingAtDayForTime(day, startTime);

        return booking
                .map(bookingDTOConverter::convert)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
