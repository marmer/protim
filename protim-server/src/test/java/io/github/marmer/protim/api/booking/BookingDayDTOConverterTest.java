package io.github.marmer.protim.api.booking;

import io.github.marmer.protim.service.booking.BookingDay;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static io.github.marmer.protim.api.booking.BookingDayDTOMatcher.isBookingDayDTO;
import static io.github.marmer.protim.test.Testdataprovider.newInstanceOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookingDayDTOConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingDayDTOConverter underTest;

    @Test
    public void testConvert_BookingDayGiven_AllRelevantFieldsShouldGetFilled()
            throws Exception {
        // Preparation
        final BookingDay bookingDay = newInstanceOf(BookingDay.class);

        // Execution
        final BookingDayDTO result = underTest.convert(bookingDay);

        // Assertion
        assertThat(result, isBookingDayDTO().withDay(equalTo(bookingDay.getDay())));
    }

}