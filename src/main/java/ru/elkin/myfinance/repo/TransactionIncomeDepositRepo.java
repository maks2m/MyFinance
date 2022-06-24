package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface TransactionIncomeDepositRepo extends BaseRepository<TransactionIncomeDeposit> {

    @Query("select t from TransactionIncomeDeposit t join fetch t.income join fetch t.deposit where t.user=:user order by t.date desc")
    List<TransactionIncomeDeposit> findAllTransaction(User user);

}
