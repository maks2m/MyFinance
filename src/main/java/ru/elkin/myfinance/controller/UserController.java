package ru.elkin.myfinance.controller;

import org.springframework.ui.Model;

import java.util.Map;

public interface UserController<T> {
    String list(Model model);
    String create(Model model);
    String edit(T item,
                Model model);
    String save(T itemFromDB,
                Map<String, String> mapModel,
                Model model);
    String delete(T item);
}
