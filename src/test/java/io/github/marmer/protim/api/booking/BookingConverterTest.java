package io.github.marmer.protim.api.booking;

import io.github.marmer.protim.service.booking.Booking;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.api.booking.BookingDTOTestdata.newBookingDTO;
import static io.github.marmer.protim.service.booking.BookingMatcher.isBooking;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingConverter underTest;

    @Test
    public void testConvert_BookingDTOGiven_ResultShouldContainAllRelevantValues()
            throws Exception {
        // Preparation
        final BookingDTO bookingDbo = newBookingDTO();

        // Execution
        final Booking result = underTest.convert(bookingDbo);

        // Assertion
        assertThat(result, isBooking()
                .withStartTime(bookingDbo.getStartTime())
                .withDuration(bookingDbo.getDuration())
                .withDescription(bookingDbo.getDescription())
                .withNotes(bookingDbo.getNotes())
                .withTicket(bookingDbo.getTicket()));
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final Booking result = underTest.convert((BookingDTO) null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

}