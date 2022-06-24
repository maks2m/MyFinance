package ru.elkin.myfinance.controller.mvc;

import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.User;

import java.util.Map;

public interface TransactionController {
    String list(Model model,
                User user);
    String create(Model model,
                  User user);
    String edit(Long id,
                Model model,
                User user);
    String save(Long id,
                Map<String, String> model,
                User user) throws CloneNotSupportedException;
    String delete(Long id);

}
