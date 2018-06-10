package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.api.converter.Converter;
import io.github.marmer.protim.api.dto.BookingDayDTO;
import io.github.marmer.protim.service.crud.BookingDayService;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
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
    private Converter<BookingDay, BookingDayDTO> bookingDayToBookingDayDTOConverter;

    @Test
    public void testGetDay_DayEsists_ShouldShowDay()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2012, Month.DECEMBER, 21);
        final BookingDay bookingDay = BookingDay.builder().day(date).build();
        when(bookingDayService.getBookingDay(date)).thenReturn(ofNullable(bookingDay));
        when(bookingDayToBookingDayDTOConverter.convert(bookingDay)).thenReturn(new BookingDayDTO().setDay(LocalDate.of(2002, 3, 4)));

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
        when(bookingDayService.getBookingIDsForDay(LocalDate.of(2012, 12, 21))).thenReturn(asList(1L, 2L, 3L));

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids", contains(1, 2, 3)));
    }
}