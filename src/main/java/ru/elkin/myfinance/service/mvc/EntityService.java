package ru.elkin.myfinance.service.mvc;

import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface EntityService<T> {
    List<T> list(User user);
    T create();
    T getById(Long id);
    void save(Long id, T item, User user) throws CloneNotSupportedException;
    void delete(Long id);
}
