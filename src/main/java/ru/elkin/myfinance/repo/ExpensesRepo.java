package ru.elkin.myfinance.repo;

import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface ExpensesRepo extends BaseRepository<Expenses> {

    List<Expenses> findAllByUserOrderById(User user);

    Expenses findByIdAndUser(Long id, User user);

}
