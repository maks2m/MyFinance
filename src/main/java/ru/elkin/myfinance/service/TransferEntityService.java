package ru.elkin.myfinance.service;

import org.springframework.ui.Model;

import java.util.Map;

public interface TransferEntityService<T> {
    void list(Model model);
    void create(Model model);
    void edit(T item, Model model);
    void save(T itemFromDB, Map<String, String> model) throws CloneNotSupportedException;
    void delete(T item);
}
