package ru.elkin.myfinance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.repo.DepositRepo;
import ru.elkin.myfinance.repo.IncomeRepo;
import ru.elkin.myfinance.repo.TransactionIncomeDepositRepo;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionIncomeDepositService implements TransferEntityService<TransactionIncomeDeposit> {

    private final DepositRepo depositRepo;
    private final IncomeRepo incomeRepo;
    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;

    @Autowired
    public TransactionIncomeDepositService(DepositRepo depositRepo,
                                           IncomeRepo incomeRepo,
                                           TransactionIncomeDepositRepo transactionIncomeDepositRepo) {
        this.depositRepo = depositRepo;
        this.incomeRepo = incomeRepo;
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("transactionIncomeDepositList", transactionIncomeDepositRepo.findAllTransaction());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("transactionIncomeDeposit",new TransactionIncomeDeposit());
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        model.addAttribute("selectedIncome", new Income());
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", new Deposit());
    }

    @Override
    public void edit(TransactionIncomeDeposit item, Model model) {
        model.addAttribute("transactionIncomeDeposit", item);
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        model.addAttribute("selectedIncome", item.getIncome());
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("selectedDeposit", item.getDeposit());
    }

    @Override
    public void save(TransactionIncomeDeposit itemFromDB, Map<String, String> model) throws CloneNotSupportedException {

        TransactionIncomeDeposit oldItem = new TransactionIncomeDeposit();

        if (itemFromDB == null) {
            itemFromDB = new TransactionIncomeDeposit();
        } else {
            oldItem = (TransactionIncomeDeposit) itemFromDB.clone();
            oldItem.setIncome((Income) itemFromDB.getIncome().clone());
            oldItem.setDeposit((Deposit) itemFromDB.getDeposit().clone());
        }

        itemFromDB.setIncome(incomeRepo.findById(Long.valueOf(model.get("selectedIncome"))).orElseThrow());
        itemFromDB.setDeposit(depositRepo.findById(Long.valueOf(model.get("selectedDeposit"))).orElseThrow());

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
    public void delete(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() - transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() - transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.delete(transactionIncomeDeposit);
    }

    @Transactional
    void transactionSave(TransactionIncomeDeposit transactionIncomeDeposit){
        transactionIncomeDeposit.getIncome().setFactMoney(transactionIncomeDeposit.getIncome().getFactMoney() + transactionIncomeDeposit.getMoney());
        transactionIncomeDeposit.getDeposit().setMoney(transactionIncomeDeposit.getDeposit().getMoney() + transactionIncomeDeposit.getMoney());
        depositRepo.save(transactionIncomeDeposit.getDeposit());
        incomeRepo.save(transactionIncomeDeposit.getIncome());
        transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }

    @Transactional
    void transactionEdit(TransactionIncomeDeposit transactionIncomeDeposit,
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
        transactionIncomeDepositRepo.save(transactionIncomeDeposit);
    }

}
