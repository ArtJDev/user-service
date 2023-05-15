package ru.juniorhub.userservice.services;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.juniorhub.userservice.dto.UserDto;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;
import ru.juniorhub.userservice.repositories.UserRepository;

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

    public Mono<UserDto> getProfile(Jwt principal) {
        String username = principal.getClaims().get("preferred_username").toString();
        String firstName = principal.getClaims().get("given_name").toString();
        String lastName = principal.getClaims().get("family_name").toString();
        User user = new User(null, username, firstName, lastName, null, null,
                null, null, null, null, null,
                null, null, null
        );
        return userRepository.findUserByUsername(username)
                .switchIfEmpty(userRepository.save(user)).map(savedUser -> savedUser.userDto(savedUser));
    }

    public Mono<User> updateUser(Jwt principal, UserDto userDto) {
        String username = principal.getClaims().get("preferred_username").toString();
        return userRepository.findUserByUsername(username)
                .map(userToUpdate -> new User(userToUpdate.id(),
                        userToUpdate.username(),
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.nickName(),
                        userDto.birthDate(),
                        userDto.country(),
                        userDto.city(),
                        userDto.phoneNumber(),
                        userDto.email(),
                        userDto.gitHubLink(),
                        userDto.telegram(),
                        userDto.bio(),
                        userToUpdate.roles()
                ))
                .flatMap(userRepository::save);
    }

    public Flux<UserStack> getUserStack(String username) {
        return userRepository.findStackByUsername(username);
    }
}
