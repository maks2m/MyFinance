package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.TransactionDepositExpensesDto;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.TransactionDepositExpensesMapper;
import ru.elkin.myfinance.service.rest.TransactionDepositExpensesService;

import java.util.List;

@RestController
@RequestMapping("/rest/transaction-deposit-expenses")
public class TransactionDepositExpensesController extends AbstractRestController<TransactionDepositExpensesDto, TransactionDepositExpenses, TransactionDepositExpensesService> {
    protected TransactionDepositExpensesController(TransactionDepositExpensesService service) {
        super(service);
    }

    @Override
    public List<TransactionDepositExpensesDto> index(User user) {
        return TransactionDepositExpensesMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public TransactionDepositExpensesDto getOne(Long id, User user) {
        return TransactionDepositExpensesMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public TransactionDepositExpensesDto create(TransactionDepositExpensesDto modelDto, User user) {
        TransactionDepositExpenses model = TransactionDepositExpensesMapper.INSTANCE.mapSingle(modelDto);
        return TransactionDepositExpensesMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public TransactionDepositExpensesDto update(Long id, TransactionDepositExpensesDto modelDto, User user) {
        TransactionDepositExpenses model = TransactionDepositExpensesMapper.INSTANCE.mapSingle(modelDto);
        return TransactionDepositExpensesMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }
}
