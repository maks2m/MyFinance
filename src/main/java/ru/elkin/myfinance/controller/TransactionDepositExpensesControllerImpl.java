package ru.elkin.myfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.EntityDepositServiceImpl;
import ru.elkin.myfinance.service.EntityExpensesServiceImpl;
import ru.elkin.myfinance.service.TransactionDepositExpensesServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/transaction-deposit-expenses")
public class TransactionDepositExpensesControllerImpl implements TransactionController<TransactionDepositExpenses> {

    private final TransactionDepositExpensesServiceImpl transactionDepositExpensesService;
    private final EntityDepositServiceImpl entityDepositService;
    private final EntityExpensesServiceImpl entityExpensesService;

    @Autowired
    public TransactionDepositExpensesControllerImpl(TransactionDepositExpensesServiceImpl transactionDepositExpensesService,
                                                    EntityDepositServiceImpl entityDepositService,
                                                    EntityExpensesServiceImpl entityExpensesService) {
        this.transactionDepositExpensesService = transactionDepositExpensesService;
        this.entityDepositService = entityDepositService;
        this.entityExpensesService = entityExpensesService;
    }

    @Override
    @GetMapping
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("transactionDepositExpensesList", transactionDepositExpensesService.list(user));
        return "transaction_deposit_expenses";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model,
                         @AuthenticationPrincipal User user) {
        model.addAttribute("transactionDepositExpenses",transactionDepositExpensesService.create());
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("selectedDeposit", entityDepositService.create());
        model.addAttribute("expensesList", entityExpensesService.list(user));
        model.addAttribute("selectedExpenses", entityExpensesService.create());
        return "transaction_deposit_expenses_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") TransactionDepositExpenses item,
                       Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("transactionDepositExpenses", item);
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("selectedDeposit", entityDepositService.create());
        model.addAttribute("expensesList", entityExpensesService.list(user));
        model.addAttribute("selectedExpenses", entityExpensesService.create());
        return "transaction_deposit_expenses_edit";
    }


    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) TransactionDepositExpenses itemFromDB,
                       @RequestParam Map<String, String> model,
                       @AuthenticationPrincipal User user) throws CloneNotSupportedException {
        transactionDepositExpensesService.save(itemFromDB, model, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") TransactionDepositExpenses item) {
        transactionDepositExpensesService.delete(item);
        return "redirect:/";
    }

}
