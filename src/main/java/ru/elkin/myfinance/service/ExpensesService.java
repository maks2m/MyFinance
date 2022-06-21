package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.repo.ExpensesRepo;

@Service
public class ExpensesService implements EntityService<Expenses> {

    private final ExpensesRepo expensesRepo;

    @Autowired
    public ExpensesService(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("expensesList", expensesRepo.findAllByOrderById());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("expenses", new Expenses());
    }

    @Override
    public void edit(Expenses item, Model model) {
        model.addAttribute("expenses", item);
    }

    @Override
    public void save(Expenses itemFromDB, Expenses item) {
        if (itemFromDB == null) itemFromDB = new Expenses();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        expensesRepo.save(itemFromDB);
    }

    @Override
    public void delete(Expenses item) {
        expensesRepo.delete(item);
    }
}
