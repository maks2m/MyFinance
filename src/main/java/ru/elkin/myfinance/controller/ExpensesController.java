package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.service.ExpensesService;

@Controller
@RequestMapping("/expenses")
public class ExpensesController implements EntityController<Expenses> {

    private final ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        expensesService.list(model);
        return "expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        expensesService.create(model);
        return "expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Expenses item, Model model) {
        expensesService.edit(item, model);
        return "expenses_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Expenses itemFromDB,
                       @ModelAttribute("expenses") Expenses item) {
        expensesService.save(itemFromDB, item);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Expenses item) {
        expensesService.delete(item);
        return "redirect:/";
    }
}
