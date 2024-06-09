package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;
    private com.capgemini.wsb.fitnesstracker.user.api.UserDto UserDto;

    @GetMapping("/simple")
    public ResponseEntity<List<UserDetailDto>> getAllUsers() {
        return ok(userService.findAllUsers()
                .stream()
                .map(userMapper::toUserDetailDto)
                .toList());
    }

    @GetMapping()
    public ResponseEntity<List<com.capgemini.wsb.fitnesstracker.user.api.UserDto>> getAllUsersDetails() {
        return ok(userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<com.capgemini.wsb.fitnesstracker.user.api.UserDto> getUser(@PathVariable Long userId) {
        final Optional<User> optionalUser = userService.getUser(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        return ok(userMapper.toDto(optionalUser.get()));
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userMapper.toEntity(UserDto)), CREATED);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(userId, NO_CONTENT);
    }

    @GetMapping("/search/age")
    public ResponseEntity<List<UserDto>> findUsersOlderThan(@RequestParam int age) {
        List<UserDto> users = userService.findUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .collect(toList());
        return ok(users);
    }

//  @GetMapping("/getUsersOlderThanDate/{time}")
//  public ResponseEntity<List<UserDto>> getUsersOlderThanGivenAge(@PathVariable LocalDate time) {
//        final List<User> olderUsers = userService.getUsersOlderThanDate(time);
//        if(olderUsers.isEmpty()) {
//            throw new UserNotFoundException(valueOf(String.valueOf(time)));
//        }
//        return ok(olderUsers.stream().map(userMapper::toDto).collect(toList()));
//    }

    /**
     * Endpoint do pobierania listy użytkowników starszych niż podana data.
     *
     * @param date Data w formacie yyyy-MM-dd, od której mają być wyszukiwani starsi użytkownicy.
     * @return ResponseEntity zawierający listę użytkowników starszych niż podana data i kod statusu 200.
     */
    @GetMapping("/getUsersOlderThanDate/{time}")
    public ResponseEntity<List<UserDto>> getUserOlderThan(@PathVariable("time")
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        // Pobranie użytkowników starszych niż podana data z serwisu
        List<User> users = userService.getUsersOlderThanDate(date);

        // Mapowanie listy użytkowników na listę DTO
        List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .toList();

        // Zwracanie listy użytkowników w odpowiedzi HTTP z kodem statusu 200
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDto changedUser) {
        return new ResponseEntity<>(userService.updateUser(userId, userMapper.toEntitySave(changedUser)), ACCEPTED);
    }

}
