package ru.job4j.accident.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class Rule extends BaseEntity {

    @Column(name = "name")
    private String name;

    public static Rule of(String name) {
        Rule rule = new Rule();
        rule.name = name;
        return rule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
