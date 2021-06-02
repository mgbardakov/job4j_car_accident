package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

@Repository
public class MemAccidentRepository extends MemCRUDRepository<Accident> implements AccidentRepository {
}
