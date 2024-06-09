package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Training t WHERE t.user.id = :userId")
    boolean existsByUserId(@Param("userId") Long userId);

    List<Training> findByUserId(Long userId);

    List<Training> findByEndTimeAfter(Date date);

    List<Training> findByActivityType(ActivityType activityType);

    default List<Training> findTrainingsByMaxDistance(double maxDistance) {
        return findAll().stream()
                .filter(training -> training.getDistance() <= maxDistance)
                .collect(Collectors.toList());
    }
}
