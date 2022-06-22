package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.ExpensesRepo;

import java.util.List;

@Service
public class EntityExpensesServiceImpl implements EntityService<Expenses> {

    private final ExpensesRepo expensesRepo;

    @Autowired
    public EntityExpensesServiceImpl(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    @Override
    public List<Expenses> list(User user) {
        return expensesRepo.findAllByUserOrderById(user);
    }

    @Override
    public Expenses create() {
        return new Expenses();
    }

    @Override
    public void save(Expenses itemFromDB, Expenses item, User user) {
        if (itemFromDB == null) itemFromDB = new Expenses();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setUser(user);
        expensesRepo.save(itemFromDB);
    }

    @Override
    public void delete(Expenses item) {
        expensesRepo.delete(item);
    }
}
