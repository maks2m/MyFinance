package ru.elkin.myfinance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.repo.DepositRepo;
import ru.elkin.myfinance.repo.ExpensesRepo;
import ru.elkin.myfinance.repo.TransactionDepositExpensesRepo;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionDepositExpensesService implements TransferEntityService<TransactionDepositExpenses> {

    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;

    @Autowired
    public TransactionDepositExpensesService(DepositRepo depositRepo,
                                             ExpensesRepo expensesRepo,
                                             TransactionDepositExpensesRepo transactionDepositExpensesRepo) {
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("transactionDepositExpensesList", transactionDepositExpensesRepo.findAllTransaction());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("transactionDepositExpenses",new TransactionDepositExpenses());
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", new Deposit());
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        model.addAttribute("selectedExpenses", new Expenses());
    }

    @Override
    public void edit(TransactionDepositExpenses item, Model model) {
        model.addAttribute("transactionDepositExpenses", item);
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", item.getDeposit());
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        model.addAttribute("selectedExpenses", item.getExpenses());
    }

    @Override
    public void save(TransactionDepositExpenses itemFromDB, Map<String, String> model) throws CloneNotSupportedException {

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
