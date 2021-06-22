package ru.job4j.accident.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface JpaAccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
