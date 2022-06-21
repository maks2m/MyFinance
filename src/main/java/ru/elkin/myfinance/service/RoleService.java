package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.repo.RoleRepo;

@Service
public class RoleService implements EntityService<Role> {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("roleList", roleRepo.findAllByOrderById());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("roles", new Role());
    }

    @Override
    public void edit(Role item, Model model) {
        model.addAttribute("roles", item);
    }

    @Override
    public void save(Role itemFromDB, Role item) {
        if (itemFromDB == null) itemFromDB = new Role();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        roleRepo.save(itemFromDB);

    }

    @Override
    public void delete(Role item) {
        roleRepo.delete(item);
    }
}
