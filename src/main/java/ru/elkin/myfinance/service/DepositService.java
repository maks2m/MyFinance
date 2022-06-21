package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.repo.DepositRepo;

@Service
public class DepositService implements EntityService<Deposit> {

    private final DepositRepo depositRepo;

    @Autowired
    public DepositService(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("depositList", depositRepo.findAllByOrderById());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("deposit", new Deposit());
    }

    @Override
    public void edit(Deposit item, Model model) {
        model.addAttribute("deposit", item);
    }

    @Override
    public void save(Deposit itemFromDB, Deposit item) {
        if (itemFromDB == null) itemFromDB = new Deposit();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        depositRepo.save(itemFromDB);
    }

    @Override
    public void delete(Deposit item) {
        depositRepo.delete(item);
    }
}
