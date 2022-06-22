package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.DepositRepo;

import java.util.List;

@Service
public class EntityDepositServiceImpl implements EntityService<Deposit> {

    private final DepositRepo depositRepo;

    @Autowired
    public EntityDepositServiceImpl(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    public List<Deposit> list(User user) {
        return depositRepo.findAllByUserOrderById(user);
    }

    @Override
    public Deposit create() {
        return new Deposit();
    }

    @Override
    public void save(Deposit itemFromDB, Deposit item, User user) {
        if (itemFromDB == null) itemFromDB = new Deposit();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setUser(user);
        depositRepo.save(itemFromDB);
    }

    @Override
    public void delete(Deposit item) {
        depositRepo.delete(item);
    }
}
