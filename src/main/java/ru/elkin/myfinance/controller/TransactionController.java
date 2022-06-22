package ru.elkin.myfinance.controller;

import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.User;

import java.util.Map;

public interface TransactionController<T> {
    String list(Model model,
                User user);
    String create(Model model,
                  User user);
    String edit(T item,
                Model model,
                User user);
    String save(T itemFromDB,
                Map<String, String> model,
                User user) throws CloneNotSupportedException;
    String delete(T item);

}
