package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves all users.
     *
     * @return A list of all users
     */
    List<User> findAllUsers();

    /**
     * Find users older than a specified age.
     *
     * @param age The age to compare
     * @return A list of users older than the specified age
     */
    List<User> findUsersOlderThan(int age);
}