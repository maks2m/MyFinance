package ru.elkin.myfinance.service.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.IncomeRepo;

import java.util.List;

@Service
public class IncomeService extends AbstractService<Income, IncomeRepo>{

    public IncomeService(IncomeRepo repository) {
        super(repository);
    }

    @Override
    public List<Income> index(User user) {
        return super.repository.findAllByUserOrderById(user);
    }

    @Override
    public Income create(Income entity, User user) {
        entity.setUser(user);
        entity.setId(null);
        return super.repository.save(entity);
    }

    @Override
    public Income update(Long id, Income entity, User user) {
        Income entityFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(entity, entityFromDB, "id");
        entityFromDB.setUser(user);
        return repository.save(entityFromDB);
    }

}
