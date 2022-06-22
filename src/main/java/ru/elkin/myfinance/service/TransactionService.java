package ru.elkin.myfinance.service;

import ru.elkin.myfinance.entity.User;

import java.util.List;
import java.util.Map;

public interface TransactionService<T> {
    List<T> list(User user);
    T create();
    void save(T itemFromDB,
              Map<String, String> model,
              User user) throws CloneNotSupportedException;
    void delete(T item);
}
