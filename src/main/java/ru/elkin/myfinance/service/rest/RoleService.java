package ru.elkin.myfinance.service.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.RoleRepo;

import java.util.List;

@Service
public class RoleService extends AbstractService<Role, RoleRepo> {

    public RoleService(RoleRepo repository) {
        super(repository);
    }

    @Override
    public List<Role> index(User user) {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Role getOne(Long id, User user) {
        return super.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Role create(Role entity, User user) {
        entity.setId(null);
        return super.repository.save(entity);
    }

    @Override
    public Role update(Long id, Role entity, User user) {
        Role entityFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(entity, entityFromDB, "id");
        return repository.save(entityFromDB);
    }

}
