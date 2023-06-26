package ru.juniorhub.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    Long id;
    String username;
    String firstName;
    String lastName;
    String nickName;
    String birthDate;
    String country;
    String city;
    @Pattern(regexp = "[0-9]{10}", message = "Введите 10 цифр номера телефона")
    String phoneNumber;
    String email;
    String gitHubLink;
    String telegram;
    String bio;
    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    Set<String> roles;
}