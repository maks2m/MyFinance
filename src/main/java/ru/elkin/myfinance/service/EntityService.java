package ru.elkin.myfinance.service;

import org.springframework.ui.Model;

public interface EntityService<T> {
    void list(Model model);
    void create(Model model);
    void edit(T item, Model model);
    void save(T itemFromDB, T item) throws CloneNotSupportedException;
    void delete(T item);
}
