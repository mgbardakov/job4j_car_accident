package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService accidentTypeService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        var accidentTypeId = accident.getAccidentType().getId();
        accident.setAccidentType(accidentTypeService.findTypeById(accidentTypeId));
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentTypeService.getAllTypes());
        return "accident/edit";
    }
}