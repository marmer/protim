package io.github.marmer.protim.persistence.relational.booking;

import io.github.marmer.protim.service.booking.Booking;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.booking.BookingDBOMatcher.isBookingDBO;
import static io.github.marmer.protim.service.booking.BookingTestdata.newBooking;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingDBOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingDBOConverter underTest;

    @Test
    public void testConvert_BookingDBOGiven_ResultShouldContainAllRelevantValues()
            throws Exception {
        // Preparation
        final Booking booking = newBooking();

        // Execution
        final BookingDBO result = underTest.convert(booking);

        // Assertion
        assertThat(result, isBookingDBO()
                .withStartTime(booking.getStartTime())
                .withDuration(booking.getDuration())
                .withDescription(booking.getDescription())
                .withNotes(booking.getNotes())
                .withTicket(booking.getTicket()));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final BookingDBO result = underTest.convert((Booking) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

}