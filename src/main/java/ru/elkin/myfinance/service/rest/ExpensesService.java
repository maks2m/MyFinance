package ru.elkin.myfinance.service.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.ExpensesRepo;

import java.util.List;

@Service
public class ExpensesService extends AbstractService<Expenses, ExpensesRepo> {
    public ExpensesService(ExpensesRepo repository) {
        super(repository);
    }

    @Override
    public List<Expenses> index(User user) {
        return super.repository.findAllByUserOrderById(user);
    }

    @Override
    public Expenses create(Expenses entity, User user) {
        entity.setUser(user);
        entity.setId(null);
        return super.repository.save(entity);
    }

    @Override
    public Expenses update(Long id, Expenses entity, User user) {
        Expenses entityFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(entity, entityFromDB, "id");
        entityFromDB.setUser(user);
        return repository.save(entityFromDB);
    }
}
