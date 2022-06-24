package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.DepositRepo;
import ru.elkin.myfinance.repo.IncomeRepo;
import ru.elkin.myfinance.repo.TransactionIncomeDepositRepo;
import ru.elkin.myfinance.service.util.ServiceUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TransactionIncomeDepositServiceImpl implements TransactionService<TransactionIncomeDeposit> {

    private final DepositRepo depositRepo;
    private final IncomeRepo incomeRepo;
    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;
    private final ServiceUtil serviceUtil;

    @Autowired
    public TransactionIncomeDepositServiceImpl(DepositRepo depositRepo,
                                               IncomeRepo incomeRepo,
                                               TransactionIncomeDepositRepo transactionIncomeDepositRepo,
                                               ServiceUtil serviceUtil) {
        this.depositRepo = depositRepo;
        this.incomeRepo = incomeRepo;
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<TransactionIncomeDeposit> list(User user) {
        return transactionIncomeDepositRepo.findAllTransaction(user);
    }

    @Override
    public TransactionIncomeDeposit create() {
        return new TransactionIncomeDeposit();
    }

    @Override
    public TransactionIncomeDeposit getById(Long id) {
        return transactionIncomeDepositRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Map<String, String> model, User user) throws CloneNotSupportedException {

        TransactionIncomeDeposit
                oldItem = new TransactionIncomeDeposit(),
                itemFromDB;

        if (id == null) {
            itemFromDB = new TransactionIncomeDeposit();
        } else {
            itemFromDB = getById(id);
            oldItem = (TransactionIncomeDeposit) itemFromDB.clone();
            oldItem.setIncome((Income) itemFromDB.getIncome().clone());
            oldItem.setDeposit((Deposit) itemFromDB.getDeposit().clone());
        }

        itemFromDB.setIncome(incomeRepo.findById(Long.valueOf(model.get("selectedIncome"))).orElseThrow(NotFoundException::new));
        itemFromDB.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow(NotFoundException::new));
        itemFromDB.setUser(user);

        for (String key : model.keySet()) {
            switch (key){
                case "Money":
                    itemFromDB.setMoney(Long.valueOf(model.get(key)));
                    break;
                case "Date":
                    itemFromDB.setDate(model.get(key).equals("")?null: LocalDate.parse(model.get(key)));
                    break;
            }
        }

        if (id == null) {
            serviceUtil.transactionIDSave(itemFromDB);
        } else {
            serviceUtil.transactionIDEdit(itemFromDB, oldItem);
        }

    }

    @Override
    public void delete(Long id){
        serviceUtil.transactionIDDelete(getById(id));
    }

}
