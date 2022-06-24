package ru.elkin.myfinance.service.mvc;

import ru.elkin.myfinance.entity.User;

import java.util.List;
import java.util.Map;

public interface TransactionService<T> {
    List<T> list(User user);
    T create();
    T getById(Long id);
    void save(Long id,
              Map<String, String> model,
              User user) throws CloneNotSupportedException;
    void delete(Long id);
}
