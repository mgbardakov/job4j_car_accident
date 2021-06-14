package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        var accidentTypeId = accident.getAccidentType().getId();
        accident.setAccidentType(accidentTypeService.findTypeById(accidentTypeId));
        var ruleIds = req.getParameterValues("rIds");
        if (ruleIds != null) {
            accident.setRules(getRulesFromRequest(req));
        }
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentTypeService.getAllTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/edit";
    }

    private Set<Rule> getRulesFromRequest(HttpServletRequest req) {
        var rsl = new HashSet<Rule>();
        Arrays.stream(req.getParameterValues("rIds"))
                .map(x -> ruleService.findById(Integer.parseInt(x))).forEach(rsl::add);
        return rsl;
    }
}
