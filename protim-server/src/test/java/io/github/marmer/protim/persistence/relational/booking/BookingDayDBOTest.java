package io.github.marmer.protim.persistence.relational.booking;

import org.junit.Test;

import static io.github.marmer.protim.persistence.relational.booking.BookingDayDBOMatcher.isBookingDayDBO;
import static io.github.marmer.protim.service.Converter.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
public class BookingDayDBOTest {


    @Test
    public void testAddBookings_ListContainsAllreadyElementsAndNewElementIsGiven_ShouldAddElementToList()
            throws Exception {
        // Preparation
        final BookingDayDBO underTest = new BookingDayDBO();
        final BookingDBO oldBooking = mock(BookingDBO.class, "oldBooking");
        underTest.setBookings(asList(oldBooking));
        final BookingDBO newBooking = mock(BookingDBO.class, "newBooking");

        // Execution
        underTest.addBookings(newBooking);

        // Assertion
        assertThat(underTest, isBookingDayDBO().withBookings(contains(oldBooking, newBooking)));
    }

    @Test
    public void testAddBookings_ListIsNullEndElementGiven_ListShouldGetInitializedWithGivenElement()
            throws Exception {
        // Preparation
        final BookingDayDBO underTest = new BookingDayDBO();
        underTest.setBookings(null);
        final BookingDBO booking = mock(BookingDBO.class, "oldBooking");

        // Execution
        underTest.addBookings(booking);

        // Assertion
        assertThat(underTest, isBookingDayDBO().withBookings(contains(booking)));
    }

}