package ru.juniorhub.userservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public record Avatar(
        @Id
        Long id,
        String username,
        byte[] avatar
) {
}
