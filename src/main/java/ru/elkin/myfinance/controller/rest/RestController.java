package ru.elkin.myfinance.controller.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface RestController<E> {

    @GetMapping
    List<E> index(@AuthenticationPrincipal User user);

    @GetMapping("{id}")
    E getOne(@PathVariable("id") Long id,
             @AuthenticationPrincipal User user);

    @PostMapping
    E create(@RequestBody E modelDto,
             @AuthenticationPrincipal User user);

    @PutMapping("{id}")
    E update(@PathVariable("id") Long id,
             @RequestBody E modelDto,
             @AuthenticationPrincipal User user);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id,
                @AuthenticationPrincipal User user);
}
