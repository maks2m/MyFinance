package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.myfinance.entity.Income;

import java.util.List;

public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByName(String name);
    List<Income> findAllByOrderById();
}
