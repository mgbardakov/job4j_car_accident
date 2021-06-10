package ru.job4j.accident.bootstrap;

import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

@Component
public class LoadData {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public LoadData(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
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
        ruleService.addRule(Rule.of("Cтатья. 1"));
        ruleService.addRule(Rule.of("Cтатья. 2"));
        ruleService.addRule(Rule.of("Cтатья. 3"));
    }
}
