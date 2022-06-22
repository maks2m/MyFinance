package ru.elkin.myfinance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.service.RoleServiceImpl;

@Controller
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleControllerImpl implements RoleController<Role> {

    private final RoleServiceImpl roleService;

    public RoleControllerImpl(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        model.addAttribute("roleList", roleService.list());
        return "role";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("roles", roleService.create());
        return "role_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Role item, Model model) {
        model.addAttribute("roles", item);
        return "role_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Role itemFromDB,
                       @ModelAttribute("roles") Role item) {
        roleService.save(itemFromDB, item);
        return "redirect:/role";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Role item) {
        roleService.delete(item);
        return "redirect:/role";
    }
}
