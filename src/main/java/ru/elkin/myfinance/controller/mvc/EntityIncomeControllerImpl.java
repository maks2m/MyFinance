package ru.elkin.myfinance.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.mvc.EntityIncomeServiceImpl;

@Controller
@RequestMapping("/income")
public class EntityIncomeControllerImpl implements EntityController<Income> {

    private final EntityIncomeServiceImpl incomeService;

    @Autowired
    public EntityIncomeControllerImpl(EntityIncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    @Override
    @GetMapping
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("incomeList", incomeService.list(user));
        return "income";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("income", incomeService.create());
        return "income_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("income", incomeService.getById(id));
        return "income_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("income") Income item,
                       @AuthenticationPrincipal User user) {
        incomeService.save(id, item, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        incomeService.delete(id);
        return "redirect:/";
    }

}
