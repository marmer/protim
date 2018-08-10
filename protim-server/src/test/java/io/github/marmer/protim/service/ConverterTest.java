package io.github.marmer.protim.service;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.marmer.protim.service.Converter.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    private Converter<Integer, String> underTest = new Converter<Integer, String>() {
        @Override
        public String convert(final Integer source) {
            return Integer.toString(source);
        }
    };

    @Test
    public void testConvert_CollectionWithValuesGiven_ShouldReturnListWithConvertedValues()
            throws Exception {
        // Preparation

        // Execution
        final List<String> converted = underTest.convert(asList(42, 1337));

        // Assertion
        assertThat(converted, contains("42", "1337"));
    }

    @Test
    public void testConvert_NullListGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final List<String> converted = underTest.convert((List) null);

        // Assertion
        assertThat(converted, is(nullValue()));
    }

    @Test
    public void testConvert_SetWithValuesGiven_ShouldReturnSetWithConvertedValues()
            throws Exception {
        // Preparation

        // Execution
        final Set<String> converted = underTest.convert(asSet(42, 1337));

        // Assertion
        assertThat(converted, containsInAnyOrder("42", "1337"));
    }

    private Set<Integer> asSet(final Integer... values) {
        return new HashSet<Integer>(asList(values));
    }

    @Test
    public void testConvert_NullSetGiven_ShouldReturnNull()
            throws Exception {
        // Preparation

        // Execution
        final Set<String> converted = underTest.convert((Set) null);

        // Assertion
        assertThat(converted, is(nullValue()));
    }

    @Test
    public void testAsList_ArrayGiven_ShouldReturnAModifiableList()
            throws Exception {
        // Preparation

        // Execution
        final List<Integer> result = Converter.asList(3, 4, 5);
        result.add(6);

        // Assertion
        assertThat(result, contains(3, 4, 5, 6));
    }

    @Test
    public void testAsList_NullGiven_ShouldReturnAModifiableList()
            throws Exception {
        // Preparation

        // Execution
        final List<Integer> result = Converter.asList(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testAsSet_ArrayGiven_ShouldReturnAModifiableSet()
            throws Exception {
        // Preparation

        // Execution
        final Set<Integer> result = Converter.asSet(3, 4, 5);
        result.add(6);

        // Assertion
        assertThat(result, containsInAnyOrder(3, 4, 5, 6));
    }

    @Test
    public void testAsSet_NullGiven_ShouldReturnAModifiableSet()
            throws Exception {
        // Preparation

        // Execution
        final Set<Integer> result = Converter.asSet(null);

        // Assertion
        assertThat(result, is(nullValue()));
    }
}