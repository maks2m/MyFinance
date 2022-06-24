package ru.elkin.myfinance.service.rest;

import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.TransactionIncomeDepositRepo;
import ru.elkin.myfinance.service.util.ServiceUtil;

import java.util.List;

@Service
public class TransactionIncomeDepositService extends AbstractService<TransactionIncomeDeposit, TransactionIncomeDepositRepo> {

    private final ServiceUtil serviceUtil;

    public TransactionIncomeDepositService(TransactionIncomeDepositRepo repository, ServiceUtil serviceUtil) {
        super(repository);
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<TransactionIncomeDeposit> index(User user) {
        return super.repository.findAllTransaction(user);
    }

    @Override
    public TransactionIncomeDeposit create(TransactionIncomeDeposit entity, User user) {
        entity.setUser(user);
        entity.setId(null);
        return serviceUtil.transactionIDSave(entity);
    }

    @Override
    public TransactionIncomeDeposit update(Long id, TransactionIncomeDeposit entity, User user) {
        TransactionIncomeDeposit oldEntity = getOne(id, user);
        entity.setUser(user);
        entity.setId(null);
        return serviceUtil.transactionIDEdit(entity, oldEntity);
    }

    @Override
    public void delete(Long id, User user) {
        TransactionIncomeDeposit entity = getOne(id, user);
        serviceUtil.transactionIDDelete(entity);
    }
}
