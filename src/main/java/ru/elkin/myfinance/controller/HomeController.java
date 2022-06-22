package ru.elkin.myfinance.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.*;

@Controller
public class HomeController {

    private final EntityDepositServiceImpl entityDepositService;
    private final EntityExpensesServiceImpl entityExpensesService;
    private final EntityIncomeServiceImpl entityIncomeService;
    private final TransactionIncomeDepositServiceImpl transactionIncomeDepositService;
    private final TransactionDepositExpensesServiceImpl transactionDepositExpensesService;

    public HomeController(EntityDepositServiceImpl entityDepositService,
                          EntityExpensesServiceImpl entityExpensesService,
                          EntityIncomeServiceImpl entityIncomeService,
                          TransactionIncomeDepositServiceImpl transactionIncomeDepositService,
                          TransactionDepositExpensesServiceImpl transactionDepositExpensesService) {
        this.entityDepositService = entityDepositService;
        this.entityExpensesService = entityExpensesService;
        this.entityIncomeService = entityIncomeService;
        this.transactionIncomeDepositService = transactionIncomeDepositService;
        this.transactionDepositExpensesService = transactionDepositExpensesService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @AuthenticationPrincipal User user) {
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("expensesList", entityExpensesService.list(user));
        model.addAttribute("incomeList", entityIncomeService.list(user));
        model.addAttribute("transactionIncomeDepositList", transactionIncomeDepositService.list(user));
        model.addAttribute("transactionDepositExpensesList", transactionDepositExpensesService.list(user));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
