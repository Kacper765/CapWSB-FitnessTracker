package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProviderImpl implements UserProvider {
    @Override
    public Optional<User> getUser(Long userId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public List<User> findUsersOlderThan(int age) {
        return List.of();
    }
}

