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
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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
    public void testGetDay_MultipleDaysExist_ShouldShowDay()
            throws Exception {
        // Preparation
        final BookingDBO booking = new BookingDBO().setDescription("bookingDescription");
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 2))
                        .setBookings(singletonList(booking)));
        bookingDayRepository.save(
                new BookingDayDBO()
                        .setDay(LocalDate.of(1985, Month.JANUARY, 3)));

        // Execution
        mockMvc.perform(get("/api/day/1985-01-02"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.day", equalTo("1985-01-02")))
                .andExpect(jsonPath("$.bookings", contains(booking.getId())));
    }

}