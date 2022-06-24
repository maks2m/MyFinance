package ru.elkin.myfinance.service.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.UserRepo;

import java.util.List;

@Service
public class UserService extends AbstractService<User, UserRepo> {

    public UserService(UserRepo repository) {
        super(repository);
    }

    @Override
    public List<User> index(User user) {
        return super.repository.findAllByOrderById();
    }

    @Override
    public User getOne(Long id, User user) {
        return super.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User entity, User user) {
        entity.setId(null);
        return super.repository.save(entity);
    }

    @Override
    public User update(Long id, User entity, User user) {
        User entityFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(entity, entityFromDB, "id");
        return repository.save(entityFromDB);
    }

}
