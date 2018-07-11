package io.github.marmer.protim.service.usermanagement;

import io.github.marmer.protim.service.exception.RessourceConflictException;

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
    void addUser(User user) throws RessourceConflictException;
}
