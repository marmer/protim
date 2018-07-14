package io.github.marmer.protim.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Converter class to convert objects from one type to another.
 *
 * @param <S> Source type
 * @param <D> Destination type
 */
@FunctionalInterface
public interface Converter<S, D> {
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
        final Collector<D, ?, List<D>> collector = Collectors.toList();
        return sources == null
                ? null
                : sources.stream().map(this::convert).collect(collector);
    }

    /**
     * Converts a set of S to D.
     *
     * @param sources a set of Objects to convert.
     * @return a set of converted Objects.
     */
    default Set<D> convert(final Set<? extends S> sources) {
        final Collector<D, ?, Set<D>> collector = Collectors.toSet();
        return sources == null
                ? null
                : sources.stream().map(this::convert).collect(collector);
    }
}
