package ru.juniorhub.userservice.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.juniorhub.userservice.dto.UserDto;

import javax.validation.constraints.Pattern;
import java.util.List;

@Table("users")
public record User(
        @Id
        Long id,
        String username,
        String firstName,
        String lastName,
        String nickName,
        String birthDate,
        String country,
        String city,
        @Pattern(regexp = "[0-9]{10}", message = "Введите 10 цифр номера телефона")
        String phoneNumber,
        String email,
        String gitHubLink,
        String telegram,
        @Length(max = 2000, message = "Максимум 2000 символов")
        String bio,
        @MappedCollection(idColumn = "id")
        List<String> roles
) {
    public UserDto userDto(User user) {
        return new UserDto(user.firstName(), user.lastName(), user.nickName(),
                user.birthDate(), user.country(), user.city(), user.phoneNumber(), user.email(), user.gitHubLink(),
                user.telegram(), user.bio());
    }
}
