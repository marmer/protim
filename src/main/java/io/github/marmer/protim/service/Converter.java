package io.github.marmer.protim.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converter class to convert objects from one type to another.
 *
 * @param <S> Source type
 * @param <D> Destination type
 */
@FunctionalInterface
public interface Converter<S, D> {
    static <T> List<T> asList(final T... values) {
        return values == null
                ? null
                : Stream.of(values).collect(Collectors.toList());
    }

    static <T> Set<T> asSet(final T... values) {
        return values == null
                ? null
                : Stream.of(values).collect(Collectors.toSet());
    }

    /**
     * Converts the an object from the given type into another.
     *
     * @param source Source entity.
     * @return The converted result.
     */
    D convert(S source);

    /**
     * Converts a collection of S to D.
     *
     * @param sources a collection of Objects to convert.
     * @return a list of converted Objects.
     */
    default List<D> convert(final Collection<? extends S> sources) {
        return convert(sources, Collectors.toList());
    }

    /**
     * Converts a set of S to D.
     *
     * @param sources a set of Objects to convert.
     * @return a set of converted Objects.
     */
    default Set<D> convert(final Set<? extends S> sources) {
        return convert(sources, Collectors.toSet());
    }

    default <T extends Collection<D>> T convert(final Collection<? extends S> sources, final Collector<D, ?, T> collector) {
        return sources == null
                ? null
                : sources.stream().map(this::convert).collect(collector);
    }
}
