package ru.elkin.myfinance.service.rest;

import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.TransactionDepositExpensesRepo;
import ru.elkin.myfinance.service.util.ServiceUtil;

import java.util.List;

@Service
public class TransactionDepositExpensesService extends AbstractService<TransactionDepositExpenses, TransactionDepositExpensesRepo> {

    private final ServiceUtil serviceUtil;

    public TransactionDepositExpensesService(TransactionDepositExpensesRepo repository,
                                             ServiceUtil serviceUtil) {
        super(repository);
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<TransactionDepositExpenses> index(User user) {
        return super.repository.findAllTransaction(user);
    }

    @Override
    public TransactionDepositExpenses create(TransactionDepositExpenses entity, User user) {
        entity.setUser(user);
        entity.setId(null);
        return serviceUtil.transactionDESave(entity);
    }

    @Override
    public TransactionDepositExpenses update(Long id, TransactionDepositExpenses entity, User user) {
        TransactionDepositExpenses oldEntity = getOne(id, user);
        entity.setUser(user);
        entity.setId(null);
        return serviceUtil.transactionDEEdit(entity, oldEntity);
    }

    @Override
    public void delete(Long id, User user) {
        TransactionDepositExpenses entity = getOne(id, user);
        serviceUtil.transactionDEDelete(entity);
    }
}
