package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.service.TransactionDepositExpensesService;

import java.util.Map;

@Controller
@RequestMapping("/transaction-deposit-expenses")
public class TransactionDepositExpensesController implements TransferEntityController<TransactionDepositExpenses> {

    private final TransactionDepositExpensesService transactionDepositExpensesService;

    @Autowired
    public TransactionDepositExpensesController(TransactionDepositExpensesService transactionDepositExpensesService) {
        this.transactionDepositExpensesService = transactionDepositExpensesService;
    }

    @Override
    @GetMapping
    public String list(Model model) {
        transactionDepositExpensesService.list(model);
        return "transaction_deposit_expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        transactionDepositExpensesService.create(model);
        return "transaction_deposit_expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") TransactionDepositExpenses item, Model model) {
        transactionDepositExpensesService.edit(item, model);
        return "transaction_deposit_expenses_edit";
    }


    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) TransactionDepositExpenses itemFromDB,
                       @RequestParam Map<String, String> model) throws CloneNotSupportedException {
        transactionDepositExpensesService.save(itemFromDB, model);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") TransactionDepositExpenses item) {
        transactionDepositExpensesService.delete(item);
        return "redirect:/";
    }

}
