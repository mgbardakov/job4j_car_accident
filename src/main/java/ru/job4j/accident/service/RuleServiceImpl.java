package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    public RuleServiceImpl(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Rule addRule(Rule rule) {
        return ruleRepository.create(rule);
    }

    @Override
    public List<Rule> getRules() {
        return ruleRepository.findAll(Rule.class);
    }

    @Override
    public Rule findById(int id) {
        return ruleRepository.read(Rule.class, id);
    }
}
