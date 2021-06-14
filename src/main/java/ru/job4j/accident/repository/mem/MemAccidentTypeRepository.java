package ru.job4j.accident.repository.mem;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

@Repository
@Profile("mem")
public class MemAccidentTypeRepository extends MemCRUDRepository<AccidentType> implements AccidentTypeRepository {
}
