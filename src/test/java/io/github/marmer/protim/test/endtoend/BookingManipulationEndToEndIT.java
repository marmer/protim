package io.github.marmer.protim.test.endtoend;

import io.github.marmer.protim.api.controller.BookingDayController;
import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import io.github.marmer.protim.test.DbCleanupService;
import io.github.marmer.protim.test.TransactionlessTestEntityManager;
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

import static io.github.marmer.protim.persistence.relational.dbo.BookingDBOMatcher.isBookingDBO;
import static io.github.marmer.protim.persistence.relational.dbo.BookingDayDBOMatcher.isBookingDayDBO;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingManipulationEndToEndIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookingDayController classUnderTest;

    @Autowired
    private DbCleanupService dbCleanupService;
    @Autowired
    private TransactionlessTestEntityManager entityManager;

    @Before
    public void setUp() {
        dbCleanupService.clearAll();
    }

    @Test
    public void testGetDay_MultipleDaysExist_ShouldShowRequestedDay()
            throws Exception {
        // Preparation
        entityManager.persist(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2)));
        entityManager.persist(
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
        entityManager.persist(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2))
                        .setBookings(asList(booking1, booking2, booking3)));
        entityManager.persist(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 3))
                        .setBookings(singletonList(new BookingDBO()
                                .setDescription("theOtherBooking"))));

        // Execution
        mockMvc.perform(get("/api/day/1985-01-02/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startTimes", contains(
                        "13:15",
                        "18:45",
                        "19:30")));
    }

    @Test
    public void testGetBooking_RequestForAnExistingBooking_ShouldServeTheRelatedBooking()
            throws Exception {
        // Preparation
        final BookingDBO booking1 = new BookingDBO()
                .setStartTime(LocalTime.of(18, 45))
                .setDescription("right");
        final BookingDBO booking2 = new BookingDBO()
                .setStartTime(LocalTime.of(19, 30))
                .setDescription("wrong1");
        final BookingDBO booking3 = new BookingDBO()
                .setStartTime(LocalTime.of(13, 15))
                .setDescription("wrong2");

        entityManager.persist(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2))
                        .setBookings(asList(booking1, booking2, booking3)));

        // Execution
        mockMvc.perform(get("/api/day/1985-01-02/bookings/{startTime}", "18:45"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description", is("right")));
    }

    @Test
    public void testPutBooking_EntryIsAdded_RelatedDayWithEntryShouldExist()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2014, 7, 13);
        final LocalTime startTime = LocalTime.of(16, 0);

        // Execution
        mockMvc.perform(
                put("/api/day/{day}/bookings/", day, startTime)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "    \"startTime\": \"16:00\",\n" +
                                "    \"duration\": \"01:56\",\n" +
                                "    \"description\": \"watching football\",\n" +
                                "    \"notes\": \"it's not called soccer\",\n" +
                                "    \"ticket\": \"WORLDCUP-2014\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        // Assertion
        assertThat(entityManager.findAllOf(BookingDayDBO.class), contains(isBookingDayDBO()
                .withDay(day)
                .withBookings(contains(
                        isBookingDBO()
                                .withStartTime(startTime)
                                .withDescription("watching football")
                ))));
    }

    @Test
    public void testPutBooking_EntryIsAddedForOverride_RelatedDayWithOnlyTheNewEntryShouldExist()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2014, 7, 13);
        final LocalTime startTime = LocalTime.of(16, 0);

        this.entityManager.persistAndFlush(
                new BookingDayDBO()
                        .setDay(day)
                        .setBookings(singletonList(new BookingDBO()
                                .setDescription("Doing the opposite of watching football")
                                .setStartTime(startTime))));

        // Execution
        mockMvc.perform(
                put("/api/day/{day}/bookings/", day, startTime)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "    \"startTime\": \"16:00\",\n" +
                                "    \"duration\": \"01:56\",\n" +
                                "    \"description\": \"watching football\",\n" +
                                "    \"notes\": \"it's not called soccer\",\n" +
                                "    \"ticket\": \"WORLDCUP-2014\"\n" +
                                "}"))
                .andExpect(status().isCreated());

        // Assertion
        assertThat(entityManager.findAllOf(BookingDayDBO.class), contains(isBookingDayDBO()
                .withDay(day)
                .withBookings(contains(
                        isBookingDBO()
                                .withStartTime(startTime)
                                .withDescription("watching football")
                ))));
    }

    // TODO: marmer 15.06.2018 additional URL Parameter with a startTime and allready existing at parameter start time -> refresh the old one
    // TODO: marmer 15.06.2018 additional URL Parameter with a startTime and a not existing at parameter start time -> 404
    // TODO: marmer 15.06.2018 day does not exist and start time in url given -> 404
}