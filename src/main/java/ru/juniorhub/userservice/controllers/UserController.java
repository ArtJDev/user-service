package ru.juniorhub.userservice.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ru.juniorhub.userservice.dto.UserDto;
import ru.juniorhub.userservice.entity.Avatar;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;
import ru.juniorhub.userservice.services.UserService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/profile")
    public Mono<UserDto> getProfile(@RequestHeader("username") String username) {
        return userService.getProfile(username);
    }

    @PutMapping("/profile")
    public Mono<User> updateUser(@RequestHeader("username") String username, @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }

    @GetMapping("/stack")
    public Flux<UserStack> getUserStack(@RequestHeader("username") String username) {
        return userService.getUserStack(username);
    }
}
