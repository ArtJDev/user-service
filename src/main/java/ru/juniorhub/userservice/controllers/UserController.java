package ru.juniorhub.userservice.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.juniorhub.userservice.dto.UserProfile;
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
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/profile")
    public UserProfile getProfile(@AuthenticationPrincipal Jwt principal) {
        return userService.getProfile(principal);
    }

    @PostMapping("/profile")
    public UserProfile updateUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserProfile userProfile) {
        return userService.updateUser(principal, userProfile);
    }

    @GetMapping("/stack")
    public UserStack getUserStack(@AuthenticationPrincipal Jwt principal) {
        return userService.getUserStack(principal);
    }

}