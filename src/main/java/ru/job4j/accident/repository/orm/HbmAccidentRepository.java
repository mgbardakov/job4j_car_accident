package ru.job4j.accident.repository.orm;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;

@Repository
@Profile("orm")
public class HbmAccidentRepository extends HbmCRUDRepository<Accident> implements AccidentRepository {
    public HbmAccidentRepository(SessionFactory sf) {
        super(sf);
    }

    @Override
    public List<Accident> findAll(Class<Accident> type) {
        var session = sf.getCurrentSession();
        return session.createQuery("select distinct a" +
                " from Accident a left join fetch a.rules", Accident.class).list();
    }
}
