package com.capgemini.wsb.fitnesstracker.user.internal;


import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already Database ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(final Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    @Override
    public List<User> findUsersOlderThan(int age) {
        LocalDate maxBirthdate = LocalDate.now().minusYears(age);
        return userRepository.findUsersOlderThan(maxBirthdate);
    }

    @Override
    public List<User> getUsersOlderThanDate(final LocalDate time) {
        // Pobranie wszystkich użytkowników z repozytorium
        final List<User> allUsers = userRepository.findAll();

        // Filtracja użytkowników na podstawie daty urodzenia
        List<User> olderUsers = allUsers.stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();

        // Zwrócenie listy starszych użytkowników
        return olderUsers;
    }

    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        return userRepository.save(user);
    }
}
