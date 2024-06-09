package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingService extends TrainingProvider {

    TrainingDto createTraining(CreateTrainingDto createTrainingDto);

    List<TrainingDto> findAllTrainings();

    List<TrainingDto> findTrainingsByUserId(Long userId);

    List<TrainingDto> findTrainingsByEndTimeAfter(Date date);

    List<TrainingDto> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Finds all training sessions with a maximum distance up to the specified value.
     *
     * @param distance the maximum distance of the training sessions to be retrieved.
     * @return a list of training sessions where the distance is less than or equal to the specified value.
     */
    List<Training> findTrainingsByMaxDistance(double distance);

    TrainingDto updateTraining(Long trainingId, CreateTrainingDto createTrainingDto);
}