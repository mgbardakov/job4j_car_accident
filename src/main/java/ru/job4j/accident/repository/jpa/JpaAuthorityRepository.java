package ru.job4j.accident.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

public interface JpaAuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
