package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
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
    public Expenses getById(Long id) {
        return expensesRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Expenses item, User user) {
        Expenses itemFromDB;
        if (id == null) {
            itemFromDB = new Expenses();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setUser(user);
        expensesRepo.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        expensesRepo.deleteById(id);
    }
}
