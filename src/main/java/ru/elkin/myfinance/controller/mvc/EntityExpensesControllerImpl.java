package ru.elkin.myfinance.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.mvc.EntityExpensesServiceImpl;

@Controller
@RequestMapping("/expenses")
public class EntityExpensesControllerImpl implements EntityController<Expenses> {

    private final EntityExpensesServiceImpl expensesService;

    @Autowired
    public EntityExpensesControllerImpl(EntityExpensesServiceImpl expensesService) {
        this.expensesService = expensesService;
    }

    @Override
    @GetMapping
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("expensesList", expensesService.list(user));
        return "expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("expenses", expensesService.create());
        return "expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("expenses", expensesService.getById(id));
        return "expenses_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("expenses") Expenses item,
                       @AuthenticationPrincipal User user) {
        expensesService.save(id, item, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        expensesService.delete(id);
        return "redirect:/";
    }
}
