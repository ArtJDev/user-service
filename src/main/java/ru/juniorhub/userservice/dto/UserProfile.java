package ru.juniorhub.userservice.dto;

import ru.juniorhub.userservice.entity.User;

public record UserProfile(
        String firstName,
        String lastName,
        String nickName,
        String birthDate,
        String country,
        String city,
        String phoneNumber,
        String email,
        String gitHubLink,
        String telegram,
        String bio
) {
    public static UserProfile of(User user) {
        return new UserProfile(user.getFirstName(), user.getLastName(), user.getNickName(),
                user.getBirthDate(), user.getCountry(), user.getCity(), user.getPhoneNumber(),
                user.getEmail(), user.getGitHubLink(), user.getTelegram(), user.getBio());
    }
}