package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentService {
    Accident addAccident(Accident accident);
    List<Accident> getAllAccidents();
}
