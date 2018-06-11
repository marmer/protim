package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.api.dto.BookingDTO;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.service.crud.BookingDayService;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookingDayControllerTest {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookingDayController classUnderTest;

    @MockBean
    private BookingDayService bookingDayService;
    @MockBean
    private Converter<BookingDay, BookingDayDTO> bookingDayDTOConverter;
    @MockBean
    private Converter<Booking, BookingDTO> bookingDTOConverter;

    @Test
    public void testGetDay_DayEsists_ShouldShowDay()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2012, Month.DECEMBER, 21);
        final BookingDay bookingDay = BookingDay.builder().day(date).build();
        when(bookingDayService.getBookingDay(date)).thenReturn(ofNullable(bookingDay));
        when(bookingDayDTOConverter.convert(bookingDay)).thenReturn(new BookingDayDTO().setDay(LocalDate.of(2002, 3, 4)));

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", equalTo("2002-03-04")));
    }

    @Test
    public void testGetDay_DayDoesNotExist_ShouldServeStatusNotFound()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2012, Month.DECEMBER, 21);
        final BookingDay bookingDay = BookingDay.builder().day(date).build();
        when(bookingDayService.getBookingDay(date)).thenReturn(empty());

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetDay_InvalidDateFormatChosen_ShouldServeStatusNotFound()
            throws Exception {
        // Execution
        mockMvc.perform(get("/api/day/iReallyAmNoDate"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_DayHasEntries_ShouldLiestEntries()
            throws Exception {
        // Preparation
        when(bookingDayService.getBookingStartTimesForDay(LocalDate.of(2012, 12, 21)))
                .thenReturn(asList(
                        LocalTime.of(10, 15),
                        LocalTime.of(15, 30)));

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTimes", contains("10:15", "15:30")));
    }

    @Test
    public void testGetBooking_BookingExists_ShouldServeBooking()
            throws Exception {
        // Preparation
        final Booking booking = Booking.builder().description("The only one").build();
        when(bookingDayService.getBookingForTime(
                LocalDate.of(2012, 12, 21),
                LocalTime.of(7, 13)
        )).thenReturn(Optional.of(booking));
        final BookingDTO bookingDTO = new BookingDTO().setDescription("Whoop Whoop");
        when(bookingDTOConverter.convert(booking)).thenReturn(bookingDTO);

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21/bookings/{startTime}", "07:13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Whoop Whoop")));
    }

    @Test
    public void testGetBooking_BookingDoesNotExistExist_ShouldServe404()
            throws Exception {
        // Preparation
        final Booking booking = Booking.builder().description("The only one").build();
        when(bookingDayService.getBookingForTime(
                Mockito.any(LocalDate.class),
                Mockito.any(LocalTime.class)
        )).thenReturn(Optional.of(booking));
        final BookingDTO bookingDTO = new BookingDTO().setDescription("Whoop Whoop");

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21/bookings/{startTime}", "07:13"))
                .andExpect(status().isNotFound());
    }
}