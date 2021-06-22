package ru.job4j.accident.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.jpa.JpaAccidentTypeRepository;
import ru.job4j.accident.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
@Transactional
public class JpaAccidentTypeService implements AccidentTypeService {

    private final JpaAccidentTypeRepository accidentTypeRepository;

    public JpaAccidentTypeService(JpaAccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public AccidentType findTypeById(int id) {
        return accidentTypeRepository.findById(id).orElse(null);
    }

    @Override
    public void addAccidentType(AccidentType accidentType) {
        accidentTypeRepository.save(accidentType);
    }

    @Override
    public List<AccidentType> getAllTypes() {
        var rslList = new ArrayList<AccidentType>();
        accidentTypeRepository.findAll().forEach(rslList::add);
        return rslList;
    }
}
