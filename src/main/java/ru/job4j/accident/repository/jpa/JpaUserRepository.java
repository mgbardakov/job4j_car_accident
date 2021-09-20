package ru.job4j.accident.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

import java.util.Optional;

public interface JpaUserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}
