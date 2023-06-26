package ru.juniorhub.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.juniorhub.userservice.entity.UserStack;

@Repository
public interface UserStackRepository extends JpaRepository<UserStack, Long> {

    UserStack findUserStackByUsername(String username);
}