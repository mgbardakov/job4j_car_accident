package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "accident")
public class Accident extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "address")
    private String address;
    @OneToOne
    @JoinColumn(name = "accident_type_id")
    private AccidentType accidentType;
    @ManyToMany
    @JoinTable(name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<Rule> rules = new HashSet<Rule>();

    public Accident() {
    }

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, String text, String address, AccidentType accidentType) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.accidentType = accidentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
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
