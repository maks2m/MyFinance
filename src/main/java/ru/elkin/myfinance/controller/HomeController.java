package ru.elkin.myfinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.elkin.myfinance.repo.*;

@Controller
public class HomeController {

    private final DepositRepo depositRepo;
    private final ExpensesRepo expensesRepo;
    private final IncomeRepo incomeRepo;
    private final TransactionDepositExpensesRepo transactionDepositExpensesRepo;
    private final TransactionIncomeDepositRepo transactionIncomeDepositRepo;

    public HomeController(DepositRepo depositRepo,
                          ExpensesRepo expensesRepo,
                          IncomeRepo incomeRepo,
                          TransactionDepositExpensesRepo transactionDepositExpensesRepo,
                          TransactionIncomeDepositRepo transactionIncomeDepositRepo) {
        this.depositRepo = depositRepo;
        this.expensesRepo = expensesRepo;
        this.incomeRepo = incomeRepo;
        this.transactionDepositExpensesRepo = transactionDepositExpensesRepo;
        this.transactionIncomeDepositRepo = transactionIncomeDepositRepo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
        model.addAttribute("transactionDepositExpensesList", transactionDepositExpensesRepo.findAllTransaction());
        model.addAttribute("transactionIncomeDepositList", transactionIncomeDepositRepo.findAllTransaction());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
