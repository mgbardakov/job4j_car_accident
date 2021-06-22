package ru.job4j.accident.bootstrap;

import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import java.util.Set;

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
        if (accidentService.getAllAccidents().isEmpty()) {

            var accidentType1 = AccidentType.of("Две машины");
            var accidentType2 = AccidentType.of("Машина и человек");
            var accidentType3 = AccidentType.of("Машина и велосипед");

            accidentTypeService.addAccidentType(accidentType1);
            accidentTypeService.addAccidentType(accidentType2);
            accidentTypeService.addAccidentType(accidentType3);

            var rule1 = Rule.of("Cтатья. 1");
            var rule2 = Rule.of("Cтатья. 2");
            var rule3 = Rule.of("Cтатья. 3");

            ruleService.addRule(rule1);
            ruleService.addRule(rule2);
            ruleService.addRule(rule3);

            var accident1 = new Accident("Столкновение",
                    "Столкновение 2-х машин",
                    "Перекресток Ленина-Вороширова",
                    accidentType1);
            accident1.addRule(rule1);
            accident1.addRule(rule2);
            accidentService.addAccident(accident1);
            var accident2 = new Accident("Наезд на пешехода",
                    "Травмы средней тяжести",
                    "ул. Красной Зорьки",
                    accidentType2);
            accident2.addRule(rule2);
            accidentService.addAccident(accident2);
            var accident3 = new Accident("Столкновение",
                    "Столкновение со столбом",
                    "ул. Аркадия Укупника",
                    accidentType3);
            accident3.addRule(rule3);
            accident3.addRule(rule2);
            accident2.addRule(rule1);
            accidentService.addAccident(accident3);
        }
    }
}
