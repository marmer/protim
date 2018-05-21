package io.github.marmer.protim.controller;

import io.github.marmer.protim.model.dbo.BookingDayDBO;
import io.github.marmer.protim.repositories.BookingDayRepository;
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

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class BookingDayDBOControllerTest {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookingDayController classUnderTest;

    @MockBean
    private BookingDayRepository bookingDayRepository;

    @Test
    public void testGetDay_DayEsists_ShouldShowDay()
            throws Exception {
        // Preparation
        when(bookingDayRepository.findAll()).thenReturn(singletonList(new BookingDayDBO().setDay(new GregorianCalendar(2012, Calendar.DECEMBER, 21))));

        // Execution
        mockMvc.perform(get("api/day"))
                .andExpect(jsonPath("$.day", equalTo("2012-12-21")));
    }

}