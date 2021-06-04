package ru.job4j.accident.service;

import ru.job4j.accident.model.AccidentType;

import java.util.List;

public interface AccidentTypeService {
    AccidentType findTypeById(int id);
    void addAccidentType(AccidentType accidentType);
    List<AccidentType> getAllTypes();
}
