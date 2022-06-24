package ru.elkin.myfinance.service.rest;

import ru.elkin.myfinance.entity.AbstractEntity;
import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface RestService<E extends AbstractEntity> {

    List<E> index(User user);

    E getOne(Long id,
             User user);

    E create(E entity,
             User user);

    E update(Long id,
             E entity,
             User user);

    void delete(Long id,
                User user);

}
