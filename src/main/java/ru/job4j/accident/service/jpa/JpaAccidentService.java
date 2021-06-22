package ru.job4j.accident.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.jpa.JpaAccidentRepository;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
@Transactional
public class JpaAccidentService implements AccidentService {

    private final JpaAccidentRepository accidentRepository;

    public JpaAccidentService(JpaAccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    @Override
    public Accident addAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        var rslList = new ArrayList<Accident>();
        accidentRepository.findAll().forEach(rslList::add);
        return rslList;
    }

    @Override
    public Accident findById(int id) {
        return accidentRepository.findById(id).orElse(null);
    }
}
