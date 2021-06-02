package ru.job4j.accident.bootstrap;

import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Component
public class LoadData{

    private final AccidentService accidentService;

    public LoadData(AccidentService accidentService) {
        this.accidentService = accidentService;
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
    }
}
