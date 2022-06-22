package ru.elkin.myfinance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.UserServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserControllerImpl implements UserController<User> {

    private final UserServiceImpl userService;

    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        userService.list(model);
        return "user";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        userService.create(model);
        return "user_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") User item, Model model) {
        userService.edit(item, model);
        return "user_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) User itemFromDB,
                       @RequestParam Map<String, String> mapModel,
                       Model model) {
        if (userService.save(itemFromDB, mapModel, model)) {
            return "redirect:/user";
        } else {
            return "user_edit";
        }
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") User item) {
        userService.delete(item);
        return "redirect:/user";
    }
}
