package ru.elkin.myfinance.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.mvc.EntityDepositServiceImpl;
import ru.elkin.myfinance.service.mvc.EntityExpensesServiceImpl;
import ru.elkin.myfinance.service.mvc.TransactionDepositExpensesServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/transaction-deposit-expenses")
public class TransactionDepositExpensesControllerImpl implements TransactionController {

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
    public String edit(@PathVariable("id") Long id,
                       Model model,
                       @AuthenticationPrincipal User user) {

        TransactionDepositExpenses transactionDepositExpenses = transactionDepositExpensesService.getById(id);
        model.addAttribute("transactionDepositExpenses",transactionDepositExpenses);
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("selectedDeposit", transactionDepositExpenses.getDeposit());
        model.addAttribute("expensesList", entityExpensesService.list(user));
        model.addAttribute("selectedExpenses", transactionDepositExpenses.getExpenses());
        return "transaction_deposit_expenses_edit";
    }


    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> model,
                       @AuthenticationPrincipal User user) throws CloneNotSupportedException {
        transactionDepositExpensesService.save(id, model, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        transactionDepositExpensesService.delete(id);
        return "redirect:/";
    }

}
