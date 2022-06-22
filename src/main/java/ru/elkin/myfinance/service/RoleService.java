package ru.elkin.myfinance.service;

import java.util.List;

public interface RoleService<T> {
    List<T> list();
    T create();
    void save(T itemFromDB, T item);
    void delete(T item);
}
