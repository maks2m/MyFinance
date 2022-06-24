package ru.elkin.myfinance.controller.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.service.mvc.RoleServiceImpl;
import ru.elkin.myfinance.service.mvc.UserServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public UserControllerImpl(UserServiceImpl userService,
                              RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("userList", userService.list());
        return "user";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", userService.create());
        model.addAttribute("roleList", roleService.list());
        return "user_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", roleService.list());
        return "user_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> mapModel,
                       Model model) {
        if (userService.save(id, mapModel, model)) {
            return "redirect:/user";
        } else {
            return "user_edit";
        }
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
