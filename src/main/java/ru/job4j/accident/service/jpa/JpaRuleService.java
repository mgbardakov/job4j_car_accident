package ru.job4j.accident.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.jpa.JpaRuleRepository;
import ru.job4j.accident.service.RuleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
@Transactional
public class JpaRuleService implements RuleService {

    private final JpaRuleRepository ruleRepository;

    public JpaRuleService(JpaRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Rule addRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<Rule> getRules() {
        var rslList = new ArrayList<Rule>();
        ruleRepository.findAll().forEach(rslList::add);
        return rslList;
    }

    @Override
    public Rule findById(int id) {
        return ruleRepository.findById(id).orElse(null);
    }
}
