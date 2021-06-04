package ru.job4j.accident.bootstrap;

import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

@Component
public class LoadData{

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    public LoadData(AccidentService accidentService, AccidentTypeService accidentTypeService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
    }

    public void load() {
        accidentService.addAccident(new Accident("Столкновение",
                                                 "Столкновение 2-х машин",
                                                 "Перекресток Ленина-Вороширова"));
        accidentService.addAccident(new Accident("Наезд на пешехода",
                                                 "Травмы средней тяжести",
                                                 "ул. Красной Зорьки"));
        accidentService.addAccident(new Accident("Столкновение",
                                                 "Столкновение со столбом",
                                                 "ул. Аркадия Укупника"));
        accidentTypeService.addAccidentType(AccidentType.of("Две машины"));
        accidentTypeService.addAccidentType(AccidentType.of("Машина и человек"));
        accidentTypeService.addAccidentType(AccidentType.of("Машина и велосипед"));
    }
}
