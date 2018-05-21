package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import io.github.marmer.protim.service.converter.BookingDayDBOToBookingDayConverter;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BookingDayServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingDayServiceImpl classUnderTest;
    @Mock
    private BookingDayRepository bookingDayRepository;
    @Mock
    private BookingDayDBOToBookingDayConverter bookingDayDBOToBookingDayConverter;

    @Test
    public void testGetBookingDay_BookingDayExists_ShouldDeliverBusinessModelVersion()
            throws Exception {
        // Preparation
        final Calendar date = Calendar.getInstance();
        final BookingDayDBO dbo = newBookingDayDBO();
        when(bookingDayRepository.findByDay(date)).thenReturn(dbo);
        final BookingDay bookingDay = newBookingDay();
        when(bookingDayDBOToBookingDayConverter.convert(dbo)).thenReturn(bookingDay);

        // Execution
        final Optional<BookingDay> result = classUnderTest.getBookingDay(date);

        // Assertion
        assertThat(result.get(), is(bookingDay));
    }

    private BookingDay newBookingDay() {
        return BookingDay.builder()
                .day(new GregorianCalendar(1985, Calendar.JANUARY, 3))
                .build();
    }

    private BookingDayDBO newBookingDayDBO() {
        return new BookingDayDBO()
                .setDay(new GregorianCalendar(1985, Calendar.JANUARY, 2));
    }
}