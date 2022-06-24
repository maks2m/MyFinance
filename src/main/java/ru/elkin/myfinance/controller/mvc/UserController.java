package ru.elkin.myfinance.controller.mvc;

import org.springframework.ui.Model;

import java.util.Map;

public interface UserController {
    String list(Model model);
    String create(Model model);
    String edit(Long id,
                Model model);
    String save(Long id,
                Map<String, String> mapModel,
                Model model);
    String delete(Long Id);
}
