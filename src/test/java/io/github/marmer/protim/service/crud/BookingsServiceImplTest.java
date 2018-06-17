package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import io.github.marmer.protim.persistence.relational.repositories.BookingDayRepository;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static io.github.marmer.protim.persistence.relational.dbo.testdata.BookingDBOTestdata.newBookingDBO;
import static io.github.marmer.protim.service.model.BookingDayMatcher.isBookingDay;
import static io.github.marmer.protim.service.model.BookingTestdata.newBooking;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BookingsServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    private BookingsServiceImpl classUnderTest;
    @Mock
    private BookingDayRepository bookingDayRepository;
    @Mock
    private Converter<BookingDayDBO, BookingDay> bookingDayConverter;
    @Mock
    private Converter<BookingDBO, Booking> bookingConverter;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new BookingsServiceImpl(bookingDayRepository, bookingDayConverter, bookingConverter);
    }

    @Test
    public void testGetBookingDay_BookingDayExists_ShouldDeliverBusinessModelVersion()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2020, 3, 4);
        final BookingDayDBO dbo = newBookingDayDBO();
        when(bookingDayRepository.findFirstByDayIs(date)).thenReturn(Optional.of(dbo));
        final BookingDay bookingDay = newBookingDay();
        when(bookingDayConverter.convert(dbo)).thenReturn(bookingDay);

        // Execution
        final Optional<BookingDay> result = classUnderTest.getBookingDay(date);

        // Assertion
        assertThat(result.get(), is(bookingDay));
    }

    @Test
    public void testGetBookingDay_BookingDayDoesNotExist_ShouldReturnAnInstance()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2002, 2, 2);
        when(bookingDayRepository.findFirstByDayIs(day)).thenReturn(Optional.empty());

        // Execution
        final Optional<BookingDay> bookingDay = classUnderTest.getBookingDay(day);

        // Assertion
        assertThat(bookingDay.get(), isBookingDay().withDay(day));
    }

    @Test
    public void testGetBookingStartTimesForDay_EntriesExistForTheGivenDay_ShouldReturnTheirIDs()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1985, 1, 2);
        when(bookingDayRepository.findBookingStartTimesForDay(day))
                .thenReturn(asList(
                        LocalTime.of(1, 2),
                        LocalTime.of(3, 4)));

        // Execution
        final List<LocalTime> results = classUnderTest.getBookingStartTimesForDay(day);

        // Assertion
        assertThat(results, contains(
                LocalTime.of(1, 2),
                LocalTime.of(3, 4)));
    }

    @Test
    public void testGetBookingForTime_ABookingExistsForTheRelatedDay_ShouldBeReturned()
            throws Exception {
        // Preparation
        final Booking booking = newBooking();
        final LocalDate day = LocalDate.of(2012, 3, 4);
        final LocalTime startTime = LocalTime.of(5, 6);
        final BookingDBO bookingDBO = newBookingDBO();
        when(bookingDayRepository.findBookingByStartTimeForDay(day, startTime)).thenReturn(Optional.of(bookingDBO));
        when(bookingConverter.convert(bookingDBO)).thenReturn(booking);

        // Execution
        final Optional<Booking> result = classUnderTest.getBookingAtDayForTime(day, startTime);

        // Assertion
        assertThat(result.get(), is(booking));
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