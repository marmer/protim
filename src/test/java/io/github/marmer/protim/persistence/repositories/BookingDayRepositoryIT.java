package io.github.marmer.protim.persistence.repositories;

import io.github.marmer.protim.persistence.dbo.BookingDBO;
import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.time.LocalDate;
import java.util.List;

import static io.github.marmer.protim.persistence.dbo.BookingDayDBOMatcher.isBookingDayDBO;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
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
        final BookingDayDBO bookingDay = classUnderTest.findByDay(day);

        // Assertion
        assertThat(bookingDay, isBookingDayDBO().withId(id));
    }

    @Test
    public void testFindEntryIdsFor_SomeBookingDaysWithDifferentBookingsExist_ShuoldOnlyFindBookingsForTheRelatedDay()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2002, 2, 2);
        final BookingDayDBO bookingDay = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day)
                        .setBookings(
                                asList(new BookingDBO()
                                                .setDescription("desc1"),
                                        new BookingDBO()
                                                .setDescription("desc2"))));
        final BookingDayDBO bookingDayIrrelevant = entityManager.persist(
                new BookingDayDBO()
                        .setDay(day.plusDays(1))
                        .setBookings(
                                asList(new BookingDBO()
                                                .setDescription("desc3"),
                                        new BookingDBO()
                                                .setDescription("desc4"))));

        // Execution
        final List<Long> results = classUnderTest.findBookingIdsForDay(day);

        // Assertion
        assertThat(results, contains(
                bookingDay.getBookings().get(0).getId(),
                bookingDay.getBookings().get(1).getId()));
    }

}