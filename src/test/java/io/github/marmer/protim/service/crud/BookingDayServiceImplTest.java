package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.repositories.BookingDayRepository;
import io.github.marmer.protim.service.converter.BookingDayConverter;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
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
    private BookingDayConverter bookingDayConverter;

    @Test
    public void testGetBookingDay_BookingDayExists_ShouldDeliverBusinessModelVersion()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2020, 3, 4);
        final BookingDayDBO dbo = newBookingDayDBO();
        when(bookingDayRepository.findByDay(date)).thenReturn(dbo);
        final BookingDay bookingDay = newBookingDay();
        when(bookingDayConverter.convert(dbo)).thenReturn(bookingDay);

        // Execution
        final Optional<BookingDay> result = classUnderTest.getBookingDay(date);

        // Assertion
        assertThat(result.get(), is(bookingDay));
    }

    @Test
    public void testGetEnttyIDsForDay_EntriesExistForTheGivenDay_ShouldReturnTheirIDs()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1985, 1, 2);
        when(bookingDayRepository.findEntryIdsForDay(day)).thenReturn(asList(2L, 5L, 8L));

        // Execution
        final List<Long> results = classUnderTest.getEnttyIDsForDay(day);

        // Assertion
        assertThat(results, contains(2L, 5L, 8L));
    }

    private BookingDay newBookingDay() {
        return BookingDay.builder()
                .day(LocalDate.of(1985, Month.JANUARY, 3))
                .build();
    }

    private BookingDayDBO newBookingDayDBO() {
        return new BookingDayDBO();
    }
}