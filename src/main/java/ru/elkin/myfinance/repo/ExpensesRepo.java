package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
    List<Expenses> findByName(String name);
    List<Expenses> findAllByUserOrderById(User user);
}
