package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.List;

@Service
public class AccidentTypeServiceImpl implements AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;

    public AccidentTypeServiceImpl(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public AccidentType findTypeById(int id) {
        return accidentTypeRepository.read(AccidentType.class, id);
    }

    @Override
    public void addAccidentType(AccidentType accidentType) {
        accidentTypeRepository.create(accidentType);
    }

    @Override
    public List<AccidentType> getAllTypes() {
        return accidentTypeRepository.findAll(AccidentType.class);
    }
}
