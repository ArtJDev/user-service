package ru.juniorhub.userservice.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.juniorhub.userservice.dto.UserProfile;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;
import ru.juniorhub.userservice.repositories.UserRepository;
import ru.juniorhub.userservice.repositories.UserStackRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStackRepository userStackRepository;

    public UserService(UserRepository userRepository, UserStackRepository userStackRepository) {
        this.userRepository = userRepository;
        this.userStackRepository = userStackRepository;
    }

    //Для администратора, получить список всех пользователей.
    //ToDO method security @PreAuthorize("hasRole('admin')").
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserProfile getProfile(Jwt principal) {
        String firstName = principal.getClaims().get("given_name").toString();
        String lastName = principal.getClaims().get("family_name").toString();
        Set<String> roles = Stream.of(principal.getClaims().get("roles").toString()).collect(Collectors.toSet());
        User user = new User(null, extractUsername(principal), firstName, lastName, null, null,
                null, null, null, null, null,
                null, null, roles
        );
        User savedUser = userRepository.findUserByUsername(extractUsername(principal))
                .orElseGet(() -> userRepository.save(user));
        return UserProfile.of(savedUser);
    }

    public UserProfile updateUser(Jwt principal, UserProfile userProfile) {
        User savedUser = userRepository.findUserByUsername(extractUsername(principal))
                .map(userToUpdate -> new User(userToUpdate.getId(),
                        userToUpdate.getUsername(),
                        userProfile.firstName(),
                        userProfile.lastName(),
                        userProfile.nickName(),
                        userProfile.birthDate(),
                        userProfile.country(),
                        userProfile.city(),
                        userProfile.phoneNumber(),
                        userProfile.email(),
                        userProfile.gitHubLink(),
                        userProfile.telegram(),
                        userProfile.bio(),
                        userToUpdate.getRoles()
                )).map(userRepository::save)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return UserProfile.of(savedUser);
    }

    public UserStack getUserStack(Jwt principal) {
        return userStackRepository.findUserStackByUsername(extractUsername(principal));
    }

    private String extractUsername(Jwt principal) {
        if (principal != null) {
            return principal.getClaims().get("preferred_username").toString();
        }
        return null;
    }
}