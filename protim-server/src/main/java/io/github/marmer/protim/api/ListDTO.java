package io.github.marmer.protim.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * List representation for the API.
 *
 * @param <E> The entrytype.
 */
@Data
@Accessors(chain = true)
public class ListDTO<E> {
    List<E> entries;
}
