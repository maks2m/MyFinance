package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.exception.NotFoundException;
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
    public Role getById(Long id) {
        return roleRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Role item) {
        Role itemFromDB;
        if (id == null) {
            itemFromDB = new Role();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        roleRepo.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        roleRepo.deleteById(id);
    }
}
