package ru.elkin.myfinance.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.mvc.EntityDepositServiceImpl;

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
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("deposit", depositService.getById(id));
        return "deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("deposit") Deposit item,
                       @AuthenticationPrincipal User user) {
        depositService.save(id, item, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        depositService.delete(id);
        return "redirect:/";
    }

}
