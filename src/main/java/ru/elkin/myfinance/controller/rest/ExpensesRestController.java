package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.ExpensesDto;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.Expenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.DepositMapper;
import ru.elkin.myfinance.mapper.ExpensesMapper;
import ru.elkin.myfinance.service.rest.ExpensesService;

import java.util.List;

@RestController
@RequestMapping("/rest/expenses")
public class ExpensesRestController extends AbstractRestController<ExpensesDto, Expenses, ExpensesService> {
    protected ExpensesRestController(ExpensesService service) {
        super(service);
    }

    @Override
    public List<ExpensesDto> index(User user) {
        return ExpensesMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public ExpensesDto getOne(Long id, User user) {
        return ExpensesMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public ExpensesDto create(ExpensesDto modelDto, User user) {
        Expenses model = ExpensesMapper.INSTANCE.mapSingle(modelDto);
        return ExpensesMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public ExpensesDto update(Long id, ExpensesDto modelDto, User user) {
        Expenses model = ExpensesMapper.INSTANCE.mapSingle(modelDto);
        return ExpensesMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }
}
