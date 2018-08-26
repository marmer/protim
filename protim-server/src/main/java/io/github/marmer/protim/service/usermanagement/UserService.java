package io.github.marmer.protim.service.usermanagement;

import io.github.marmer.protim.service.exception.RessourceConflictException;

import java.util.List;
import java.util.Optional;

/**
 * Service to access Userdetails
 */
public interface UserService {
    /**
     * Adds a user to the system.
     *
     * @param user User to add
     * @throws RessourceConflictException thrown if the user allready exists.
     */
    void addUser(User user);

    /**
     * Gets the user with the given Username.
     *
     * @param username Name of the user to get.
     * @return A User.
     */
    Optional<User> getUser(String username);

    /**
     * Gives a list of all usernames.
     *
     * @return List of all usernames.
     */
    List<String> getUsernames();
}
