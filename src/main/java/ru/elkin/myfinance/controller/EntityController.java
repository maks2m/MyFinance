package ru.elkin.myfinance.controller;

import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.User;

public interface EntityController<T> {
    String list(Model model,
                User user);
    String create(Model model);
    String edit(T item,
                Model model);
    String save(T itemFromDB,
                T item,
                User user);
    String delete(T item);

}
