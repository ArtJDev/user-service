package ru.juniorhub.userservice.repositories;

import org.springframework.data.r2dbc.repository.Modifying;
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

    @Modifying
    @Query("update users set first_name = :firstName, last_name = :lastName, " +
            "nick_name = :nickName, birth_date = :birthDate, country = :country, city = :city," +
            "phone_number = :phoneNumber, email = :email, git_hub_link =:gitHubLink, telegram = :telegram," +
            "bio = :bio where username = :username")
    Mono<User> updateUser(@Param("username") String username,
                             @Param("firstName") String firstName,
                             @Param("lastName") String lastName,
                             @Param("nickName") String nickName,
                             @Param("birthDate") String birthDate,
                             @Param("country") String country,
                             @Param("city") String city,
                             @Param("phoneNumber") String phoneNumber,
                             @Param("email") String email,
                             @Param("gitHubLink") String gitHubLink,
                             @Param("telegram") String telegram,
                             @Param("bio") String bio
    );
}
