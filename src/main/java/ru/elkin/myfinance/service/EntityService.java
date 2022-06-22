package ru.elkin.myfinance.service;

import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface EntityService<T> {
    List<T> list(User user);
    T create();
    void save(T itemFromDB, T item, User user) throws CloneNotSupportedException;
    void delete(T item);
}
