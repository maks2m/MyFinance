package ru.elkin.myfinance.controller.mvc;

import org.springframework.ui.Model;

public interface RoleController<T> {
    String list(Model model);
    String create(Model model);
    String edit(Long id,
                Model model);
    String save(Long id,
                T item);
    String delete(Long id);
}
