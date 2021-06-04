package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

@Repository
public class MemAccidentTypeRepository extends MemCRUDRepository<AccidentType> implements AccidentTypeRepository {
}
