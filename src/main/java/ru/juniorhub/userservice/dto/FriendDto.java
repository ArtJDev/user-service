package ru.juniorhub.userservice.dto;

import org.springframework.data.annotation.Id;

public record FriendDto(
        @Id
        Long id,
        String name
) {
}