package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

@Repository
public class MemRuleRepository extends MemCRUDRepository<Rule> implements RuleRepository {
}
