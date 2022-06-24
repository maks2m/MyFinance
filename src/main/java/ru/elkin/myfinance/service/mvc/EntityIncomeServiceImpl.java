package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.IncomeRepo;

import java.util.List;

@Service
public class EntityIncomeServiceImpl implements EntityService<Income> {

    private final IncomeRepo incomeRepo;

    @Autowired
    public EntityIncomeServiceImpl(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public List<Income> list(User user) {
        return incomeRepo.findAllByUserOrderById(user);
    }

    @Override
    public Income create() {
        return new Income();
    }

    @Override
    public Income getById(Long id) {
        return incomeRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Income item, User user) {
        Income itemFromDB;
        if (id == null) {
            itemFromDB = new Income();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setUser(user);
        incomeRepo.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        incomeRepo.deleteById(id);
    }
}
