package ru.juniorhub.userservice.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ru.juniorhub.userservice.dto.UserDto;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;
import ru.juniorhub.userservice.services.UserService;

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
    public Mono<UserDto> getProfile(@AuthenticationPrincipal Jwt principal) {
        return userService.getProfile(principal);
    }

    @PostMapping("/profile")
    public Mono<User> updateUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserDto userDto) {
        return userService.updateUser(principal, userDto);
    }

    @GetMapping("/stack")
    public Flux<UserStack> getUserStack(@RequestHeader("username") String username) {
        return userService.getUserStack(username);
    }
}
