package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;

import java.util.List;

public interface TransactionDepositExpensesRepo extends JpaRepository<TransactionDepositExpenses, Long> {
    @Query("select t from TransactionDepositExpenses t join fetch t.deposit join fetch t.expenses order by t.date desc")
    List<TransactionDepositExpenses> findAllTransaction();

}
