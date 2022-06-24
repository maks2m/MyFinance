package ru.elkin.myfinance.controller.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.service.mvc.RoleServiceImpl;

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
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.getById(id));
        return "role_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("roles") Role item) {
        roleService.save(id, item);
        return "redirect:/role";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }
}
