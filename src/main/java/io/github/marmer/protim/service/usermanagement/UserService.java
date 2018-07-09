package io.github.marmer.protim.service.usermanagement;

/**
 * Service to access Userdetails
 */
public interface UserService {
    /**
     * Adds a user to the system.
     *
     * @param user User to add
     */
    void addUser(User user);
}
