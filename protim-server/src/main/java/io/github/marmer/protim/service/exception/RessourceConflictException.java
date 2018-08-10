package io.github.marmer.protim.service.exception;

/**
 * Is thrown at resource conflicts
 */
public class RessourceConflictException extends RuntimeException {
    /**
     * Message only constructor.
     *
     * @param message error Message
     */
    public RessourceConflictException(final String message) {
        super(message);
    }
}
