package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Optional;
import java.util.List;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all training sessions.
     */
    List<Training> getAllTrainings();

    /**
     * Finds all training sessions with a maximum distance up to the specified value.
     *
     * @param maxDistance the maximum distance of the training sessions to be retrieved.
     * @return a list of training sessions where the distance is less than or equal to the specified value.
     */
    List<Training> getTrainingsByMaxDistance(double maxDistance);

}
