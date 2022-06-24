package ru.elkin.myfinance.repo;

import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface IncomeRepo extends BaseRepository<Income> {

    List<Income> findAllByUserOrderById(User user);
    Income findByIdAndUser(Long id, User user);
}
