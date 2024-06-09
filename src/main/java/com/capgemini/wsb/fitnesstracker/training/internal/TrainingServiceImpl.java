package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

//    @Autowired
//    public TrainingServiceImpl(UserProvider userProvider) {
//        this.userProvider = userProvider;
//    }

    @Override
    public TrainingDto createTraining(final CreateTrainingDto createTrainingDto) {
        User user = userProvider.getUser(createTrainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(createTrainingDto.getUserId()));
        Training training = trainingMapper.toEntity(createTrainingDto, user);
        return trainingMapper.toDto(trainingRepository.save(training));
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId).map(Training::getUser);
    }

    @Override
    public List<TrainingDto> findAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }


    @Override
    public List<TrainingDto> findTrainingsByUserId(final Long userId) {
        return trainingRepository.findByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> findTrainingsByEndTimeAfter(final Date date) {
        return trainingRepository.findByEndTimeAfter(date)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> findTrainingsByActivityType(final ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public com.capgemini.wsb.fitnesstracker.training.api.TrainingDto updateTraining(final Long trainingId, final CreateTrainingDto createTrainingDto) {
        User user = userProvider.getUser(createTrainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(createTrainingDto.getUserId()));
        return trainingRepository.findById(trainingId)
                .map(existingTraining -> {
                    existingTraining.setStartTime(createTrainingDto.getStartTime());
                    existingTraining.setEndTime(createTrainingDto.getEndTime());
                    existingTraining.setActivityType(createTrainingDto.getActivityType());
                    existingTraining.setDistance(createTrainingDto.getDistance());
                    existingTraining.setAverageSpeed(createTrainingDto.getAverageSpeed());
                    return trainingMapper.toDto(trainingRepository.save(existingTraining));
                })
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
    }

    public Training processTrainingEntity(CreateTrainingTO createTrainingTO) {
        User user = userProvider.getUser(createTrainingTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(createTrainingTO.getUserId()));
        Training training = new Training(
                user,
                createTrainingTO.getStartTime(),
                createTrainingTO.getEndTime(),
                createTrainingTO.getActivityType(),
                createTrainingTO.getDistance(),
                createTrainingTO.getAverageSpeed()
        );
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTrainingsByMaxDistance(double maxDistance) {
        // Wywołuje metodę repozytorium, aby znaleźć treningi o maksymalnej odległości mniejszej lub równej maxDistance
        return trainingRepository.findTrainingsByMaxDistance(maxDistance);
    }

    @Override
    public List<Training> findTrainingsByMaxDistance(double maxDistance) {
        // Wywołuje metodę repozytorium, aby znaleźć treningi o maksymalnej odległości mniejszej lub równej maxDistance
        return trainingRepository.findTrainingsByMaxDistance(maxDistance);
    }
}
