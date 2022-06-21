package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.service.DepositService;

@Controller
@RequestMapping("/deposit")
public class DepositController implements EntityController<Deposit> {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        depositService.list(model);
        return "deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        depositService.create(model);
        return "deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Deposit item, Model model) {
        depositService.edit(item, model);
        return "deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Deposit itemFromDB,
                       @ModelAttribute("deposit") Deposit item) {
        depositService.save(itemFromDB, item);
        return "redirect:/";
    }
    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Deposit item) {
        depositService.delete(item);
        return "redirect:/";
    }

}
