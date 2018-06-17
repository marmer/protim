package io.github.marmer.protim.service.converter;

/**
 * Converter class to convert objects from one type to another.
 *
 * @param <S> Source type
 * @param <D> Destination type
 */
public interface Converter<S, D> {
    /**
     * Converts the an object from the given type into another.
     *
     * @param source Source entity.
     * @return The converted result.
     */
    D convert(S source);
}
