package ru.juniorhub.userservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("user_stack")
public record UserStack(
        @Id
        Long id,
        String username,
        String specialty,
        @MappedCollection(idColumn = "id")
        List<String> stack
) {
}
