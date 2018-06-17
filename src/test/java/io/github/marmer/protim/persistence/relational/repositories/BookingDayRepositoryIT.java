package io.github.marmer.protim.persistence.relational.repositories;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDayDBO;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static io.github.marmer.protim.persistence.relational.dbo.BookingDayDBOMatcher.isBookingDayDBO;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@DataJpaTest
public class BookingDayRepositoryIT {
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private BookingDayRepository classUnderTest;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByDay_SomeBookingDaysExist_ShouldFindOnlyTheBookingDayForTheRelatedDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2012, 3, 4);
        entityManager.persist(new BookingDayDBO().setDay(day.plusDays(1)));
        final Long id = entityManager.persistAndGetId(new BookingDayDBO().setDay(day), Long.class);

        // Execution
        final Optional<BookingDayDBO> bookingDay = classUnderTest.findFirstByDayIs(day);

        // Assertion
        assertThat(bookingDay.get(), isBookingDayDBO().withId(id));
    }

    @Test
    public void testFindBookingStartTimesForDay_SomeBookingDaysWithDifferentBookingsExist_ShuoldOnlyFindBookingsForTheRelatedDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2002, 2, 2);
        final BookingDayDBO bookingDay = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day)
                        .setBookings(
                                asList(new BookingDBO()
                                                .setStartTime(LocalTime.of(1, 2)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(5, 6)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(3, 4))
                                )));
        final BookingDayDBO bookingDayIrrelevant = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day.plusDays(1))
                        .setBookings(
                                asList(new BookingDBO()
                                                .setStartTime(LocalTime.of(5, 6)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(7, 8)))));

        // Execution
        final List<LocalTime> results = classUnderTest.findBookingStartTimesForDay(day);

        // Assertion
        assertThat(results, contains(
                bookingDay.getBookings().get(0).getStartTime(),
                bookingDay.getBookings().get(2).getStartTime(),
                bookingDay.getBookings().get(1).getStartTime()));
    }

    @Test
    public void testFindBookingByStartTimeForDay_SomeBookingDaysWithDifferentBookingsExist_ShuoldOnlyFindBookingsForTheRelatedDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2002, 2, 2);
        final BookingDayDBO bookingDay = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day)
                        .setBookings(
                                asList(new BookingDBO()
                                                .setStartTime(LocalTime.of(1, 2)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(5, 6)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(3, 4))
                                )));
        final BookingDayDBO bookingDayIrrelevant = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day.plusDays(1))
                        .setBookings(
                                asList(new BookingDBO()
                                                .setStartTime(LocalTime.of(5, 6)),
                                        new BookingDBO()
                                                .setStartTime(LocalTime.of(7, 8)))));

        // Execution
        final Optional<BookingDBO> result = classUnderTest.findBookingByStartTimeForDay(day, LocalTime.of(5, 6));

        // Assertion
        assertThat(result.get(), is(bookingDay.getBookings().get(1)));
    }
}