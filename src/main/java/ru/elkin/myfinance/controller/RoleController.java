package ru.elkin.myfinance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.service.RoleService;

@Controller
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController implements EntityController<Role> {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        roleService.list(model);
        return "role";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        roleService.create(model);
        return "role_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Role item, Model model) {
        roleService.edit(item, model);
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
