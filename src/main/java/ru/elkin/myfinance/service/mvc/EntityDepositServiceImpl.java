package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
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
    public Deposit getById(Long id) {
        return depositRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Long id, Deposit item, User user) {
        Deposit itemFromDB;
        if (id == null) {
            itemFromDB = new Deposit();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setUser(user);
        depositRepo.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        depositRepo.deleteById(id);
    }
}
