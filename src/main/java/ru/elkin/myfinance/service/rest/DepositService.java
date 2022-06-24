package ru.elkin.myfinance.service.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.DepositRepo;

import java.util.List;

@Service
public class DepositService extends AbstractService<Deposit, DepositRepo>{

    public DepositService(DepositRepo repository) {
        super(repository);
    }

    @Override
    public List<Deposit> index(User user) {
        return super.repository.findAllByUserOrderById(user);
    }

    @Override
    public Deposit create(Deposit entity, User user) {
        entity.setUser(user);
        entity.setId(null);
        return super.repository.save(entity);
    }

    @Override
    public Deposit update(Long id, Deposit entity, User user) {
        Deposit entityFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(entity, entityFromDB, "id");
        entityFromDB.setUser(user);
        return repository.save(entityFromDB);
    }

}
