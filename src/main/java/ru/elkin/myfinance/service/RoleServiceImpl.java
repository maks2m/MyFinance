package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.repo.RoleRepo;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService<Role> {

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> list() {
        return roleRepo.findAllByOrderById();
    }

    @Override
    public Role create() {
        return new Role();
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
