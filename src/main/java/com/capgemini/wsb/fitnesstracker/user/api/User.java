package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Getter
    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.birthdate = birthdate;
                this.email = email;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

}

