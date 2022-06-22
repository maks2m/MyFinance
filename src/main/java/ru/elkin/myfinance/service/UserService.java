package ru.elkin.myfinance.service;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Map;

public interface UserService<T> {
    void list(Model model);
    void create(Model model);
    void edit(T item,
              Model model);
    boolean save(T itemFromDB,
              Map<String, String> mapModel,
              Model model);
    void delete(T item);
}
