package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(CreateTrainingDto trainingDto, User user) {
        return new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }
}