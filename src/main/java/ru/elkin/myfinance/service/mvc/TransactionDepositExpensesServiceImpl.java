package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.DepositRepo;
import ru.elkin.myfinance.repo.ExpensesRepo;
import ru.elkin.myfinance.repo.TransactionDepositExpensesRepo;
import ru.elkin.myfinance.service.util.ServiceUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TransactionDepositExpensesServiceImpl implements TransactionService<TransactionDepositExpenses> {

    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;
    private final ServiceUtil serviceUtil;

    @Autowired
    public TransactionDepositExpensesServiceImpl(DepositRepo depositRepo,
                                                 ExpensesRepo expensesRepo,
                                                 TransactionDepositExpensesRepo transactionDepositExpensesRepo,
                                                 ServiceUtil serviceUtil) {
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<TransactionDepositExpenses> list(User user) {
        return transactionDepositExpensesRepo.findAllTransaction(user);
    }

    @Override
    public TransactionDepositExpenses create() {
        return new TransactionDepositExpenses();
    }

    @Override
    public TransactionDepositExpenses getById(Long id) {
        return transactionDepositExpensesRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Map<String, String> model, User user) throws CloneNotSupportedException {

        TransactionDepositExpenses
                oldItem = new TransactionDepositExpenses(),
                itemFromDB = new TransactionDepositExpenses();
        if (id != null) {
            itemFromDB = getById(id);
            oldItem = (TransactionDepositExpenses) itemFromDB.clone();
            oldItem.setDeposit((Deposit) itemFromDB.getDeposit().clone());
            oldItem.setExpenses((Expenses) itemFromDB.getExpenses().clone());
        }

        itemFromDB.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow(NotFoundException::new));
        itemFromDB.setExpenses(expensesRepo.findById(Long.valueOf(model.get("selectedExpenses"))).orElseThrow(NotFoundException::new));
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
            serviceUtil.transactionDESave(itemFromDB);
        } else {
            serviceUtil.transactionDEEdit(itemFromDB, oldItem);
        }

    }

    @Override
    public void delete(Long id) {
        serviceUtil.transactionDEDelete(getById(id));
    }
}
