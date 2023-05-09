package ru.juniorhub.userservice.services;

import io.r2dbc.spi.Parameter;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.juniorhub.userservice.dto.UserDto;
import ru.juniorhub.userservice.entity.Avatar;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;
import ru.juniorhub.userservice.repositories.UserRepository;

import java.io.*;

@Service
public class UserService {

    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Для администратора, получить список всех пользователей.
    //ToDO method security @PreAuthorize("hasRole('admin')").
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<UserDto> getProfile(String username) {
        User user = new User(null, username,null, null,null, null,
                null, null, null, null, null,
                null, null, null
        );
        Mono<UserDto> userDto = userRepository.findUserByUsername(username)
                .switchIfEmpty(userRepository.save(user)).map(savedUser -> savedUser.userDto(savedUser));

        return userDto;
    }

    public Mono<User> updateUser(String username, UserDto userDto) {
        return userRepository.updateUser(username, userDto.firstName(),
                userDto.lastName(), userDto.nickName(), userDto.birthDate(), userDto.country(),
                userDto.city(), userDto.phoneNumber(), userDto.email(), userDto.gitHubLink(),
                userDto.telegram(), userDto.bio()
        );

    }

    public Flux<UserStack> getUserStack(String username) {
        return userRepository.findStackByUsername(username);
    }


}
