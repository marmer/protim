package io.github.marmer.protim.service.converter;

import io.github.marmer.protim.persistence.dbo.BookingDayDBO;
import io.github.marmer.protim.service.model.BookingDay;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.GregorianCalendar;

import static io.github.marmer.protim.service.model.BookingDayMatcher.isBookingDay;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingDayDBOToBookingDayConverterTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @InjectMocks
    private BookingDayDBOToBookingDayConverter classUnderTest;

    @Test
    public void testConvert_BookingDayGiven_ShouldConvertAllRelevantProperties()
            throws Exception {
        // Preparation

        // Execution
        final BookingDayDBO dbo = newBookingDayDBO();
        final BookingDay result = classUnderTest.convert(dbo);

        // Assertion
        assertThat(result, isBookingDay()
                .withDay(dbo.getDay())
                .withId(dbo.getId())
        );
    }

    @Test
    public void testConvert_NullGiven_ShouldReturnNUll()
            throws Exception {
        // Preparation

        // Execution
        final BookingDay result = classUnderTest.convert(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

    private BookingDayDBO newBookingDayDBO() {
        return new BookingDayDBO()
                .setDay(new GregorianCalendar(2002, 2, 20))
                .setId(42L);
    }

}