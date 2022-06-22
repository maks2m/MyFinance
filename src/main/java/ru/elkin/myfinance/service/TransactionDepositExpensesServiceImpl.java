package ru.elkin.myfinance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.DepositRepo;
import ru.elkin.myfinance.repo.ExpensesRepo;
import ru.elkin.myfinance.repo.TransactionDepositExpensesRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionDepositExpensesServiceImpl implements TransactionService<TransactionDepositExpenses> {

    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;

    @Autowired
    public TransactionDepositExpensesServiceImpl(DepositRepo depositRepo,
                                                 ExpensesRepo expensesRepo,
                                                 TransactionDepositExpensesRepo transactionDepositExpensesRepo) {
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
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
    public void save(TransactionDepositExpenses itemFromDB, Map<String, String> model, User user) throws CloneNotSupportedException {

        TransactionDepositExpenses oldItem = new TransactionDepositExpenses();
        if (itemFromDB == null) {
            itemFromDB = new TransactionDepositExpenses();
        } else {
            oldItem = (TransactionDepositExpenses) itemFromDB.clone();
            oldItem.setDeposit((Deposit) itemFromDB.getDeposit().clone());
            oldItem.setExpenses((Expenses) itemFromDB.getExpenses().clone());
        }

        itemFromDB.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow());
        itemFromDB.setExpenses(expensesRepo.findById(Long.valueOf(model.get("selectedExpenses"))).orElseThrow());
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

        if (itemFromDB.getId() == null) {
            transactionSave(itemFromDB);
        } else {
            transactionEdit(itemFromDB, oldItem);
        }

    }

    @Override
    @Transactional
    public void delete(TransactionDepositExpenses item) {
        item.getDeposit().setMoney(item.getDeposit().getMoney() + item.getMoney());
        item.getExpenses().setFactMoney(item.getExpenses().getFactMoney() - item.getMoney());
        depositRepo.save(item.getDeposit());
        expensesRepo.save(item.getExpenses());
        transactionDepositExpensesRepo.delete(item);
    }


    @Transactional
    void transactionSave(TransactionDepositExpenses transactionDepositExpenses){
        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() - transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() + transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }

    @Transactional
    void transactionEdit(TransactionDepositExpenses transactionDepositExpenses,
                         TransactionDepositExpenses oldTransactionDepositExpenses){

        if (Objects.equals(oldTransactionDepositExpenses.getDeposit().getId(), transactionDepositExpenses.getDeposit().getId())) {
            transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() + oldTransactionDepositExpenses.getMoney());
        } else {
            oldTransactionDepositExpenses.getDeposit().setMoney(oldTransactionDepositExpenses.getDeposit().getMoney() + oldTransactionDepositExpenses.getMoney());
            depositRepo.save(oldTransactionDepositExpenses.getDeposit());
        }

        if (Objects.equals(oldTransactionDepositExpenses.getExpenses().getId(), transactionDepositExpenses.getExpenses().getId())) {
            transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() - oldTransactionDepositExpenses.getMoney());
        } else {
            oldTransactionDepositExpenses.getExpenses().setFactMoney(oldTransactionDepositExpenses.getExpenses().getFactMoney() - oldTransactionDepositExpenses.getMoney());
            expensesRepo.save(oldTransactionDepositExpenses.getExpenses());
        }

        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() - transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() + transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }
}
