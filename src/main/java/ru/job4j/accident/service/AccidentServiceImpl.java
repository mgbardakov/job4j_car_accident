package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;

@Service
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentServiceImpl(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    @Override
    public Accident addAccident(Accident accident) {
        return accidentRepository.create(accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll(Accident.class);
    }
}
