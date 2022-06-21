package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;

import java.util.List;

public interface TransactionIncomeDepositRepo extends JpaRepository<TransactionIncomeDeposit, Long> {
    @Query("select t from TransactionIncomeDeposit t join fetch t.income join fetch t.deposit order by t.date desc")
    List<TransactionIncomeDeposit> findAllTransaction();

}
