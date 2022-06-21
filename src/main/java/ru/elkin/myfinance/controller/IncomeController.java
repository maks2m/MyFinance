package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.service.IncomeService;

@Controller
@RequestMapping("/income")
public class IncomeController implements EntityController<Income> {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        incomeService.list(model);
        return "income";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        incomeService.create(model);
        return "income_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Income item, Model model) {
        incomeService.edit(item, model);
        return "income_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Income itemFromDB,
                       @ModelAttribute("income") Income item) {
        incomeService.save(itemFromDB, item);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Income item) {
        incomeService.delete(item);
        return "redirect:/";
    }

}
