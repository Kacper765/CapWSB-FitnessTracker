package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

public interface EmailProvider {
    EmailDto createEmail(final String recipient, final String title, final List<TrainingDto> trainings);
}
