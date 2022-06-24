package ru.elkin.myfinance.service.mvc;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import ru.elkin.myfinance.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService<T> {
    List<T> list();
    T create();
    T getById(Long id);
    boolean save(Long id,
              Map<String, String> mapModel,
              Model model);
    void delete(Long id);
}
