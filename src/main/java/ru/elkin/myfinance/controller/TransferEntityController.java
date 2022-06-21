package ru.elkin.myfinance.controller;

import org.springframework.ui.Model;

import java.util.Map;

public interface TransferEntityController<T> {
    String list(Model model);
    String create(Model model);
    String edit(T item, Model model);
    String save(T itemFromDB, Map<String, String> model) throws CloneNotSupportedException;
    String delete(T item);

}
