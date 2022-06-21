package ru.elkin.myfinance.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.repo.IncomeRepo;

@Service
public class IncomeService implements EntityService<Income> {

    private final IncomeRepo incomeRepo;

    @Autowired
    public IncomeService(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public void list(Model model) {
        model.addAttribute("incomeList", incomeRepo.findAllByOrderById());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("income", new Income());
    }

    @Override
    public void edit(Income item, Model model) {
        model.addAttribute("income", item);
    }

    @Override
    public void save(Income itemFromDB, Income item) {
        if (itemFromDB == null) itemFromDB = new Income();
        BeanUtils.copyProperties(item, itemFromDB, "id");
        incomeRepo.save(itemFromDB);
    }

    @Override
    public void delete(Income item) {
        incomeRepo.delete(item);
    }
}
