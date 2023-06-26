package ru.juniorhub.userservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_stack")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStack {
    @Id
    Long id;
    String username;
    String specialty;
    String stack;
    Boolean approved;
}