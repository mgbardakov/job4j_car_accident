package ru.job4j.accident.repository.mem;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;

@Repository
@Profile("mem")
public class MemRuleRepository extends MemCRUDRepository<Rule> implements RuleRepository {

    private final AccidentRepository accidentRepository;

    public MemRuleRepository(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }
}
