package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface DepositRepo extends JpaRepository<Deposit, Long> {
    List<Deposit> findByName(String name);
    List<Deposit> findAllByUserOrderById(User user);


}
