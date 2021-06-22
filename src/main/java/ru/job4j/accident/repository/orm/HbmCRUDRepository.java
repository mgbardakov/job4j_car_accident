package ru.job4j.accident.repository.orm;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import ru.job4j.accident.model.BaseEntity;
import ru.job4j.accident.repository.CRUDRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Profile("orm")
public abstract class HbmCRUDRepository<T extends BaseEntity> implements CRUDRepository<T> {

    protected final SessionFactory sf;

    public HbmCRUDRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public T create(T model) {
        var session = sf.getCurrentSession();
        session.save(model);
        return model;
    }

    @Override
    public T read(Class<T> type, Integer id) {
        var session = sf.getCurrentSession();
        return session.get(type, id);
    }

    @Override
    public void update(T model) {
        var session = sf.getCurrentSession();
        session.update(model);
    }

    @Override
    public void delete(T model) {
        var session = sf.getCurrentSession();
        session.delete(model);
    }

    @Override
    public List<T> findAll(Class<T> type) {
        var session = sf.getCurrentSession();
        var cr = session.getCriteriaBuilder().createQuery(type);
        cr.select(cr.from(type));
        return session.createQuery(cr).getResultList();
    }
}
