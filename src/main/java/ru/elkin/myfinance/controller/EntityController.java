package ru.elkin.myfinance.controller;

import org.springframework.ui.Model;

public interface EntityController<T> {
    String list(Model model);
    String create(Model model);
    String edit(T item,
                Model model);
    String save(T itemFromDB,
                T item);
    String delete(T item);

}
