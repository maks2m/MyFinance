package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.service.TransactionIncomeDepositService;

import java.util.Map;

@Controller
@RequestMapping("/transaction-income-deposit")
public class TransactionIncomeDepositController implements TransferEntityController<TransactionIncomeDeposit> {

    private final TransactionIncomeDepositService transactionIncomeDepositService;

    @Autowired
    public TransactionIncomeDepositController(TransactionIncomeDepositService transactionIncomeDepositService) {
        this.transactionIncomeDepositService = transactionIncomeDepositService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        transactionIncomeDepositService.list(model);
        return "transaction_income_deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        transactionIncomeDepositService.create(model);
        return "transaction_income_deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") TransactionIncomeDeposit item, Model model) {
        transactionIncomeDepositService.edit(item, model);
        return "transaction_income_deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) TransactionIncomeDeposit itemFromDB,
                       @RequestParam Map<String, String> model) throws CloneNotSupportedException {
        transactionIncomeDepositService.save(itemFromDB, model);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") TransactionIncomeDeposit item) {
        transactionIncomeDepositService.delete(item);
        return "redirect:/";
    }

}
