package ru.job4j.accident.service;

import ru.job4j.accident.model.Rule;

import java.util.List;

public interface RuleService {
    Rule addRule(Rule rule);
    List<Rule> getRules();
    Rule findById(int id);
}
