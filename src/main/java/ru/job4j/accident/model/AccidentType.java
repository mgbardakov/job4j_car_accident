package ru.job4j.accident.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accident_type")
public class AccidentType extends BaseEntity {

    @Column(name = "name")
    private String name;

    public AccidentType() {
    }

    public static AccidentType of(String name) {
        AccidentType type = new AccidentType();
        type.name = name;
        return type;
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