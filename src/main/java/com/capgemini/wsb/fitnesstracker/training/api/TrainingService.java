package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.Date;
import java.util.List;

public interface TrainingService extends TrainingProvider {

    TrainingDto createTraining(CreateTrainingDto createTrainingDto);

    List<TrainingDto> findAllTrainings();

    List<TrainingDto> findTrainingsByUserId(Long userId);

    List<TrainingDto> findTrainingsByEndTimeAfter(Date date);

    List<TrainingDto> findTrainingsByActivityType(ActivityType activityType);

    TrainingDto updateTraining(Long trainingId, CreateTrainingDto createTrainingDto);
}