package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.persistence.relational.booking.BookingCrudServiceRelational;
import io.github.marmer.protim.persistence.relational.booking.BookingDBO;
import io.github.marmer.protim.persistence.relational.booking.BookingDayDBO;
import io.github.marmer.protim.persistence.relational.booking.BookingDayRepository;
import io.github.marmer.protim.service.converter.Converter;
import io.github.marmer.protim.service.model.Booking;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.github.marmer.protim.persistence.relational.booking.BookingDBOTestdata.newBookingDBO;
import static io.github.marmer.protim.persistence.relational.booking.BookingDayDBOMatcher.isBookingDayDBO;
import static io.github.marmer.protim.service.model.BookingDayMatcher.isBookingDay;
import static io.github.marmer.protim.service.model.BookingTestdata.newBooking;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BookingCrudServiceRelationalTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    private BookingCrudServiceRelational classUnderTest;
    @Mock
    private BookingDayRepository bookingDayRepository;
    @Mock
    private Converter<BookingDayDBO, BookingDay> bookingDayConverter;
    @Mock
    private Converter<BookingDBO, Booking> bookingConverter;
    @Mock
    private Converter<Booking, BookingDBO> bookingDboConverter;
    @Captor
    private ArgumentCaptor<BookingDayDBO> bookingDayDBOCaptor;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new BookingCrudServiceRelational(bookingDayRepository, bookingDayConverter, bookingConverter, bookingDboConverter);
    }

    @Test
    public void testGetBookingDay_BookingDayExists_ShouldDeliverBusinessModelVersion()
            throws Exception {
        // Preparation
        final LocalDate date = LocalDate.of(2020, 3, 4);
        final BookingDayDBO dbo = newBookingDayDBO();
        when(bookingDayRepository.findFirstByDay(date)).thenReturn(Optional.of(dbo));
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
        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.empty());

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

    @Test
    public void testSetBookingAtDay_BookingDayEsistsAllreadyWithBookings_ShouldAddGivenBookingToDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final Booking booking = newBooking();
        final BookingDBO bookingDBO = newBookingDBO();
        when(bookingDboConverter.convert(booking)).thenReturn(bookingDBO);
        final BookingDayDBO bookingDayDbo = newBookingDayDBO();
        final BookingDBO oldBookingDBO = newBookingDBO();
        bookingDayDbo.setBookings(asList(oldBookingDBO));
        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDayDbo));

        // Execution
        classUnderTest.setBookingAtDay(newBookingChangeRequestWith().day(day).booking(booking).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDayDBOCaptor.capture());
        assertThat(bookingDayDBOCaptor.getValue(), is(allOf(
                sameInstance(bookingDayDbo),
                isBookingDayDBO()
                        .withBookings(contains(oldBookingDBO, bookingDBO)))));
    }

    private BookingChangeRequest.BookingChangeRequestBuilder newBookingChangeRequestWith() {
        return BookingChangeRequest.builder();
    }

    @Test
    public void testSetBookingAtDay_BookingDayEsistsAllreadyWithBookingWithTheSameTime_ShouldAddGivenBookingToDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(15, 38);
        final Booking booking = newBooking()
                .withStartTime(startTime);
        final BookingDBO bookingDBO = newBookingDBO();
        when(bookingDboConverter.convert(booking)).thenReturn(bookingDBO);
        final BookingDayDBO bookingDayDbo = newBookingDayDBO();
        final BookingDBO oldBookingDBO = newBookingDBO();
        oldBookingDBO.setStartTime(startTime);
        bookingDayDbo.setBookings(asList(oldBookingDBO));
        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDayDbo));

        // Execution
        classUnderTest.setBookingAtDay(newBookingChangeRequestWith().day(day).booking(booking).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDayDBOCaptor.capture());
        assertThat(bookingDayDBOCaptor.getValue(), is(allOf(
                sameInstance(bookingDayDbo),
                isBookingDayDBO()
                        .withBookings(contains(bookingDBO)))));
    }

    @Test
    public void testSetBookingAtDay_BookingDayEsistsAllreadyWithBookingWithTheOldTime_ShouldAddGivenBookingToDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(15, 38);
        final LocalTime oldStartTime = LocalTime.of(12, 34);
        final Booking booking = newBooking()
                .withStartTime(startTime);
        final BookingDBO bookingDBO = newBookingDBO();
        when(bookingDboConverter.convert(booking)).thenReturn(bookingDBO);
        final BookingDayDBO bookingDayDbo = newBookingDayDBO();
        final BookingDBO oldBookingDBO = newBookingDBO();
        oldBookingDBO.setStartTime(oldStartTime);
        bookingDayDbo.setBookings(asList(oldBookingDBO));
        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDayDbo));
        // Execution
        classUnderTest.setBookingAtDay(newBookingChangeRequestWith().day(day).booking(booking).startTime(oldStartTime).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDayDBOCaptor.capture());
        assertThat(bookingDayDBOCaptor.getValue(), is(allOf(
                sameInstance(bookingDayDbo),
                isBookingDayDBO()
                        .withBookings(contains(bookingDBO)))));
    }

    @Test
    public void testSetBookingAtDay_BookingDayDoesNotExistYet_ShuoldCreateBookingDayAndAddBooking()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final Booking booking = newBooking();
        final BookingDBO bookingDBO = newBookingDBO();
        when(bookingDboConverter.convert(booking)).thenReturn(bookingDBO);
        final BookingDayDBO bookingDayDbo = newBookingDayDBO();
        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.empty());

        // Execution
        classUnderTest.setBookingAtDay(newBookingChangeRequestWith().day(day).booking(booking).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDayDBOCaptor.capture());
        assertThat(bookingDayDBOCaptor.getValue(), is(
                isBookingDayDBO()
                        .withBookings(contains(bookingDBO))));
    }

    @Test
    public void testDelete_BookingDayWithMoreThenOneBookingExists_OnlyTheBookingWithTheGivenTimehasBeenRemoved()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(12, 34);

        final BookingDBO bookingToDelete = newBookingDBO()
                .setStartTime(startTime);
        final BookingDBO bookingToKeep = newBookingDBO()
                .setStartTime(startTime.plusHours(1));

        final BookingDayDBO bookingDay = newBookingDayDBO()
                .setDay(day)
                .setBookings(asList(
                        bookingToDelete,
                        bookingToKeep
                ));

        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDay));

        // Execution
        classUnderTest.delete(newBookingChangeRequestWith().day(day).startTime(startTime).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDay);
        assertThat(bookingDay.getBookings(), contains(bookingToKeep));
    }

    @Test
    public void testDelete_BookingDayWithOnlyTheRequestedBookingExists_BookingShouldBeDeleted()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(12, 34);

        final BookingDBO bookingToDelete = newBookingDBO()
                .setStartTime(startTime);

        final BookingDayDBO bookingDay = newBookingDayDBO()
                .setDay(day)
                .setBookings(asList(bookingToDelete));

        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDay));

        // Execution
        classUnderTest.delete(newBookingChangeRequestWith().day(day).startTime(startTime).build());

        // Assertion
        verify(bookingDayRepository).save(bookingDay);
        assertThat(bookingDay.getBookings(), is(empty()));
    }


    @Test
    public void testDelete_DayDoesNotExist_ShouldNotSaveAnything()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(12, 34);

        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.empty());

        // Execution
        classUnderTest.delete(newBookingChangeRequestWith().day(day).startTime(startTime).build());

        // Assertion
        verifyNoMoreInteractions(bookingDayRepository);
    }

    @Test
    public void testDelete_NoBookingExistsForTheGivenDay_ShouldNotSaveAnything()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2112, 12, 21);
        final LocalTime startTime = LocalTime.of(12, 34);

        final BookingDayDBO bookingDay = newBookingDayDBO()
                .setDay(day)
                .setBookings(emptyList());

        when(bookingDayRepository.findFirstByDay(day)).thenReturn(Optional.of(bookingDay));

        // Execution
        classUnderTest.delete(newBookingChangeRequestWith().day(day).startTime(startTime).build());

        // Assertion
        verifyNoMoreInteractions(bookingDayRepository);
    }

    private <T> List<T> asList(final T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
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