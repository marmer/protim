package io.github.marmer.protim.controller;

import io.github.marmer.protim.model.dbo.BookingDayDBO;
import io.github.marmer.protim.repositories.BookingDayRepository;
import io.github.marmer.protim.test.DbCleanupService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingDayDBOControllerIT {
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
    public void testGetDay_DayExists_ShouldShowDay()
            throws Exception {
        // Preparation
        final BookingDayDBO entity = new BookingDayDBO().setDay(new GregorianCalendar(1985, Calendar.JANUARY, 2));
        bookingDayRepository.save(entity);
        bookingDayRepository.flush();

        // Execution
        mockMvc.perform(get("api/day"))
                .andExpect(jsonPath("$.day", equalTo("1985-01-02")));
    }

}