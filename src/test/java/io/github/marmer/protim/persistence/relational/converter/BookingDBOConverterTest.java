package io.github.marmer.protim.persistence.relational.converter;

import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.service.model.Booking;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.dbo.BookingDBOMatcher.isBookingDBO;
import static io.github.marmer.protim.service.model.BookingTestdata.newBooking;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingDBOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingDBOConverter classUnderTest;

    @Test
    public void testConvert_BookingDBOGiven_ResultShouldContainAllRelevantValues()
            throws Exception {
        // Preparation
        final Booking booking = newBooking();

        // Execution
        final BookingDBO result = classUnderTest.convert(booking);

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
        final BookingDBO result = classUnderTest.convert(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

}