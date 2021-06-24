package ru.job4j.accident.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

public interface JpaUserRepository extends CrudRepository<User, Integer> {
}
