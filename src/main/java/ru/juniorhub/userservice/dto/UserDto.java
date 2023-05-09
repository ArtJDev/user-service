package ru.juniorhub.userservice.dto;

public record UserDto(
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
}
