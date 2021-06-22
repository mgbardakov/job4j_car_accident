package ru.job4j.accident.repository.orm;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

@Repository
@Profile("orm")
public class HbmAccidentTypeRepository extends HbmCRUDRepository<AccidentType> implements AccidentTypeRepository {
    public HbmAccidentTypeRepository(SessionFactory sf) {
        super(sf);
    }
}
