package ru.elkin.myfinance.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.elkin.myfinance.entity.AbstractEntity;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.rest.RestService;

import java.util.List;

public abstract class AbstractRestController <D, E extends AbstractEntity, S extends RestService<E>> implements RestController<D> {

    protected final S service;

    @Autowired
    protected AbstractRestController(S service) {
        this.service = service;
    }

/*
    @Override
    public List<D> index(User user) {
        return service.index(user);
    }

    @Override
    public D getOne(Long id, User user) {
        return service.getOne(id, user);
    }

    @Override
    public D create(D model, User user) {
        return service.create(model, user);
    }

    @Override
    public D update(Long id, D model, User user) {
        return service.update(id, model, user);
    }
*/
    @Override
    public void delete(Long id, User user) {
        service.delete(id, user);
    }

}
