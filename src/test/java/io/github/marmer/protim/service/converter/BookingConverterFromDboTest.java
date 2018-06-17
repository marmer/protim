package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.persistence.relational.converter.BookingConverterFromDBO;
import io.github.marmer.protim.persistence.relational.dbo.BookingDBO;
import io.github.marmer.protim.service.model.Booking;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.persistence.relational.dbo.testdata.BookingDBOTestdata.newBookingDBO;
import static io.github.marmer.protim.service.model.BookingMatcher.isBooking;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingConverterFromDBOTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingConverterFromDBO classUnderTest;

    @Test
    public void testConvert_BookingDBOGiven_ResultShouldContainAllRelevantValues()
            throws Exception {
        // Preparation
        final BookingDBO bookingDbo = newBookingDBO();

        // Execution
        final Booking result = classUnderTest.convert(bookingDbo);

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
        final Booking result = classUnderTest.convert(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

}