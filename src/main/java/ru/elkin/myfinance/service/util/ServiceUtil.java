package ru.elkin.myfinance.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.repo.*;

import java.util.Objects;

@Component
public class ServiceUtil {

    private final IncomeRepo incomeRepo;
    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;

    @Autowired
    public ServiceUtil(IncomeRepo incomeRepo,
                       DepositRepo depositRepo,
                       ExpensesRepo expensesRepo,
                       TransactionIncomeDepositRepo transactionIncomeDepositRepo,
                       TransactionDepositExpensesRepo transactionDepositExpensesRepo) {
        this.incomeRepo = incomeRepo;
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
    }

    @Transactional
    public void transactionDEDelete(TransactionDepositExpenses transactionDepositExpenses) {
        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() + transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() - transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        transactionDepositExpensesRepo.delete(transactionDepositExpenses);
    }


    @Transactional
    public TransactionDepositExpenses transactionDESave(TransactionDepositExpenses transactionDepositExpenses){
        transactionDepositExpenses.getDeposit().setMoney(transactionDepositExpenses.getDeposit().getMoney() - transactionDepositExpenses.getMoney());
        transactionDepositExpenses.getExpenses().setFactMoney(transactionDepositExpenses.getExpenses().getFactMoney() + transactionDepositExpenses.getMoney());
        depositRepo.save(transactionDepositExpenses.getDeposit());
        expensesRepo.save(transactionDepositExpenses.getExpenses());
        return transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }

    @Transactional
    public TransactionDepositExpenses transactionDEEdit(TransactionDepositExpenses transactionDepositExpenses,
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
        return transactionDepositExpensesRepo.save(transactionDepositExpenses);
    }
    @Transactional
    public void transactionIDDelete(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() - transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() - transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.delete(transactionIncomeDeposit);
    }

    @Transactional
    public TransactionIncomeDeposit transactionIDSave(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() + transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() + transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        return transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }

    @Transactional
    public TransactionIncomeDeposit transactionIDEdit(TransactionIncomeDeposit transactionIncomeDeposit,
                         TransactionIncomeDeposit oldTransactionIncomeDeposit){

        if (Objects.equals(oldTransactionIncomeDeposit.getIncome().getId(), transactionIncomeDeposit.getIncome().getId())) {
            transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() - oldTransactionIncomeDeposit.getMoney());
        } else {
            oldTransactionIncomeDeposit.getIncome().setFactMoney(oldTransactionIncomeDeposit.getIncome().getFactMoney() - oldTransactionIncomeDeposit.getMoney());
            incomeRepo.save(oldTransactionIncomeDeposit.getIncome());
        }

        if (Objects.equals(oldTransactionIncomeDeposit.getDeposit().getId(), transactionIncomeDeposit.getDeposit().getId())) {
            transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() - oldTransactionIncomeDeposit.getMoney());
        } else {
            oldTransactionIncomeDeposit.getDeposit().setMoney(oldTransactionIncomeDeposit.getDeposit().getMoney() - oldTransactionIncomeDeposit.getMoney());
            depositRepo.save(oldTransactionIncomeDeposit.getDeposit());
        }

        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() + transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() + transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        return transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }
}
