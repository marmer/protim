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

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Optional.ofNullable;
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
        final GregorianCalendar date = new GregorianCalendar(2012, Calendar.DECEMBER, 21);
        final BookingDay bookingDay = BookingDay.builder().day(date).build();
        when(bookingDayService.getBookingDay(date)).thenReturn(ofNullable(bookingDay));
        when(bookingDayToBookingDayDTOConverter.convert(bookingDay)).thenReturn(ofNullable(new BookingDayDTO().setDay("1122-33-44")));

        // Execution
        mockMvc.perform(get("/api/day/2012-12-21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", equalTo("1122-33-44")));
    }

    // TODO: marmer 21.05.2018 does not exist.
    // TODO: marmer 21.05.2018 date is not valid
    // TODO: marmer 21.05.2018 no conversion result
}