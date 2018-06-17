package io.github.marmer.protim.persistence.relational.dbo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.marmer.protim.persistence.relational.dbo.BookingDayDBOMatcher.isBookingDayDBO;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BookingDayDBOTest {


    @Test
    public void testAddBookings_ListContainsAllreadyElementsAndNewElementIsGiven_ShouldAddElementToList()
            throws Exception {
        // Preparation
        final BookingDayDBO classUnderTest = new BookingDayDBO();
        final BookingDBO oldBooking = mock(BookingDBO.class, "oldBooking");
        classUnderTest.setBookings(asList(oldBooking));
        final BookingDBO newBooking = mock(BookingDBO.class, "newBooking");

        // Execution
        classUnderTest.addBookings(newBooking);

        // Assertion
        assertThat(classUnderTest, isBookingDayDBO().withBookings(contains(oldBooking, newBooking)));
    }

    @Test
    public void testAddBookings_ListIsNullEndElementGiven_ListShouldGetInitializedWithGivenElement()
            throws Exception {
        // Preparation
        final BookingDayDBO classUnderTest = new BookingDayDBO();
        classUnderTest.setBookings(null);
        final BookingDBO booking = mock(BookingDBO.class, "oldBooking");

        // Execution
        classUnderTest.addBookings(booking);

        // Assertion
        assertThat(classUnderTest, isBookingDayDBO().withBookings(contains(booking)));
    }

    private <T> List<T> asList(final T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}