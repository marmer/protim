package io.github.marmer.protim.api.controller;

import io.github.marmer.protim.persistence.dbo.BookingDBO;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import io.github.marmer.protim.test.DbCleanupService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingDayControllerIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookingDayController classUnderTest;

    @Autowired
    private BookingDayRepository bookingDayRepository;

    @Autowired
    private DbCleanupService dbCleanupService;

    @Before
    public void setUp() {
        dbCleanupService.clearAll();
    }

    @Test
    public void testGetDay_MultipleDaysExist_ShouldShowRequestedDay()
            throws Exception {
        // Preparation
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2)));
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 3)));

        // Execution
        mockMvc.perform(get("/api/day/1985-01-02"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.day", equalTo("1985-01-02")));
    }

    @Test
    public void testGetEntries_BookingsForMultipleDaysExist_ShouldListBookingsForRequestedDays()
            throws Exception {
        // Preparation
        final BookingDBO booking1 = new BookingDBO().setStartTime(LocalTime.of(18, 45));
        final BookingDBO booking2 = new BookingDBO().setStartTime(LocalTime.of(19, 30));
        final BookingDBO booking3 = new BookingDBO().setStartTime(LocalTime.of(13, 15));
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2))
                        .setBookings(asList(booking1, booking2, booking3)));
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 3))
                        .setBookings(singletonList(new BookingDBO()
                                .setDescription("theOtherBooking"))));

        // Execution
        mockMvc.perform(get("/api/day/1985-01-02/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startTimes", contains(
                        booking3.getStartTime(),
                        booking1.getStartTime(),
                        booking2.getStartTime())));
    }
}