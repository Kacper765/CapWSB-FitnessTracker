package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where lower(u.email) like lower(concat('%', ?1, '%'))")
    List<User> findByEmail(final String email);
    }

