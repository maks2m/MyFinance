package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.EntityIncomeServiceImpl;

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
    public String edit(@PathVariable("id") Income item, Model model) {
        model.addAttribute("income", item);
        return "income_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Income itemFromDB,
                       @ModelAttribute("income") Income item,
                       @AuthenticationPrincipal User user) {
        incomeService.save(itemFromDB, item, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Income item) {
        incomeService.delete(item);
        return "redirect:/";
    }

}
