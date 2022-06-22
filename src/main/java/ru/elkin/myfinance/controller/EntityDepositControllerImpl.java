package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.EntityDepositServiceImpl;

@Controller
@RequestMapping("/deposit")
public class EntityDepositControllerImpl implements EntityController<Deposit> {

    private final EntityDepositServiceImpl depositService;

    @Autowired
    public EntityDepositControllerImpl(EntityDepositServiceImpl depositService) {
        this.depositService = depositService;
    }

    @Override
    @GetMapping
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("depositList", depositService.list(user));
        return "deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("deposit", depositService.create());
        return "deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Deposit item, Model model) {
        model.addAttribute("deposit", item);
        return "deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Deposit itemFromDB,
                       @ModelAttribute("deposit") Deposit item,
                       @AuthenticationPrincipal User user) {
        depositService.save(itemFromDB, item, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Deposit item) {
        depositService.delete(item);
        return "redirect:/";
    }

}
