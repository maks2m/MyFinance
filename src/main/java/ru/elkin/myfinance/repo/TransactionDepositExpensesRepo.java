package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface TransactionDepositExpensesRepo extends BaseRepository<TransactionDepositExpenses> {

    @Query("select t from TransactionDepositExpenses t join fetch t.deposit join fetch t.expenses where t.user=:user order by t.date desc")
    List<TransactionDepositExpenses> findAllTransaction(User user);

    TransactionDepositExpenses findByIdAndUser(Long id, User user);

}
