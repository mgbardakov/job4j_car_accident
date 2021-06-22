package ru.job4j.accident.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface JpaRuleRepository extends CrudRepository<Rule, Integer> {
}
