package ru.juniorhub.userservice.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.juniorhub.userservice.entity.User;
import ru.juniorhub.userservice.entity.UserStack;

public interface UserRepository extends R2dbcRepository<User, Long> {
    Mono<User> findUserByUsername(String username);

    @Query("select specialty, stack from user_stack where username = :username")
    Flux<UserStack> findStackByUsername(@Param("username") String username);

}
