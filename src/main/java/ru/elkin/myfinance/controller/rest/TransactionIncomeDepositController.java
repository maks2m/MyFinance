package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.TransactionIncomeDepositDto;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.TransactionIncomeDepositMapper;
import ru.elkin.myfinance.service.rest.TransactionIncomeDepositService;

import java.util.List;

@RestController
@RequestMapping("/rest/transaction-income-deposit")
public class TransactionIncomeDepositController extends AbstractRestController<TransactionIncomeDepositDto, TransactionIncomeDeposit, TransactionIncomeDepositService> {
    protected TransactionIncomeDepositController(TransactionIncomeDepositService service) {
        super(service);
    }

    @Override
    public List<TransactionIncomeDepositDto> index(User user) {
        return TransactionIncomeDepositMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public TransactionIncomeDepositDto getOne(Long id, User user) {
        return TransactionIncomeDepositMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public TransactionIncomeDepositDto create(TransactionIncomeDepositDto modelDto, User user) {
        TransactionIncomeDeposit model = TransactionIncomeDepositMapper.INSTANCE.mapSingle(modelDto);
        return TransactionIncomeDepositMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public TransactionIncomeDepositDto update(Long id, TransactionIncomeDepositDto modelDto, User user) {
        TransactionIncomeDeposit model = TransactionIncomeDepositMapper.INSTANCE.mapSingle(modelDto);
        return TransactionIncomeDepositMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }
}
