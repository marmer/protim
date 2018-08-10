package io.github.marmer.protim.service.usermanagement;

/**
 * Service to encode a password of a user.
 */
public interface UserpasswordEncodingService {
    /**
     * Returns the user with an encoded password.
     *
     * @param user The user for password encoding
     * @return The user but with an encoded password.
     */
    public User getWithEncodedPassword(final User user);
}
