package ru.elkin.myfinance.controller.mvc;

import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.User;

public interface EntityController<T> {
    String list(Model model,
                User user);
    String create(Model model);
    String edit(Long id,
                Model model);
    String save(Long id,
                T item,
                User user);
    String delete(Long id);

}
