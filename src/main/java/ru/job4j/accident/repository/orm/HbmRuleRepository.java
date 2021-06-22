package ru.job4j.accident.repository.orm;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

@Repository
@Profile("orm")
public class HbmRuleRepository extends HbmCRUDRepository<Rule> implements RuleRepository {
    public HbmRuleRepository(SessionFactory sf) {
        super(sf);
    }
}
