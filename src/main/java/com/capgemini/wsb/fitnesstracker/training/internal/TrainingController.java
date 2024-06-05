package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId);
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainingsAfter(@PathVariable String afterTime) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(afterTime);
            return trainingService.findTrainingsByEndTimeAfter(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody CreateTrainingDto createTrainingDto) {
        return trainingService.createTraining(createTrainingDto);
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody CreateTrainingDto createTrainingDto) {
        return trainingService.updateTraining(trainingId, createTrainingDto);
    }
}