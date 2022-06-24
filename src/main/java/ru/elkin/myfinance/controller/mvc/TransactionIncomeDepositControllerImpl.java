package ru.elkin.myfinance.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.service.mvc.EntityDepositServiceImpl;
import ru.elkin.myfinance.service.mvc.EntityIncomeServiceImpl;
import ru.elkin.myfinance.service.mvc.TransactionIncomeDepositServiceImpl;

import java.util.Map;

@Controller
@RequestMapping("/transaction-income-deposit")
public class TransactionIncomeDepositControllerImpl implements TransactionController {

    private final TransactionIncomeDepositServiceImpl transactionIncomeDepositService;
    private final EntityIncomeServiceImpl entityIncomeService;
    private final EntityDepositServiceImpl entityDepositService;

    @Autowired
    public TransactionIncomeDepositControllerImpl(TransactionIncomeDepositServiceImpl transactionIncomeDepositService,
                                                  EntityIncomeServiceImpl entityIncomeService,
                                                  EntityDepositServiceImpl entityDepositService) {
        this.transactionIncomeDepositService = transactionIncomeDepositService;
        this.entityIncomeService = entityIncomeService;
        this.entityDepositService = entityDepositService;
    }

    @Override
    @GetMapping
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("transactionIncomeDepositList", transactionIncomeDepositService.list(user));
        return "transaction_income_deposit";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model,
                         @AuthenticationPrincipal User user) {
        model.addAttribute("transactionIncomeDeposit",transactionIncomeDepositService.create());
        model.addAttribute("incomeList", entityIncomeService.list(user));
        model.addAttribute("selectedIncome", entityIncomeService.create());
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("selectedDeposit", entityDepositService.create());
        return "transaction_income_deposit_edit";
    }

    @Override
    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model,
                       @AuthenticationPrincipal User user) {
        TransactionIncomeDeposit transactionIncomeDeposit = transactionIncomeDepositService.getById(id);
        model.addAttribute("transactionIncomeDeposit", transactionIncomeDeposit);
        model.addAttribute("incomeList", entityIncomeService.list(user));
        model.addAttribute("selectedIncome", transactionIncomeDeposit.getIncome());
        model.addAttribute("depositList", entityDepositService.list(user));
        model.addAttribute("selectedDeposit", transactionIncomeDeposit.getDeposit());
        return "transaction_income_deposit_edit";
    }

    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> model,
                       @AuthenticationPrincipal User user) throws CloneNotSupportedException {
        transactionIncomeDepositService.save(id, model, user);
        return "redirect:/";
    }

    @Override
    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        transactionIncomeDepositService.delete(id);
        return "redirect:/";
    }

}
