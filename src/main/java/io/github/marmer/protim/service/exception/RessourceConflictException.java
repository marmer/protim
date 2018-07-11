package io.github.marmer.protim.service.exception;

/**
 * Is thrown at resource conflicts
 */
public class RessourceConflictException extends RuntimeException {
    public RessourceConflictException(final String s) {
        super(s);
    }
}
