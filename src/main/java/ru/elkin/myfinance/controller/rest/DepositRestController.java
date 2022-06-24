package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.DepositDto;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.DepositMapper;
import ru.elkin.myfinance.service.rest.DepositService;

import java.util.List;

@RestController
@RequestMapping("/rest/deposit")
public class DepositRestController extends AbstractRestController<DepositDto, Deposit, DepositService> {
    protected DepositRestController(DepositService service) {
        super(service);
    }

    @Override
    public List<DepositDto> index(User user) {
        return DepositMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public DepositDto getOne(Long id, User user) {
        return DepositMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public DepositDto create(DepositDto modelDto, User user) {
        Deposit model = DepositMapper.INSTANCE.mapSingle(modelDto);
        return DepositMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public DepositDto update(Long id, DepositDto modelDto, User user) {
        Deposit model = DepositMapper.INSTANCE.mapSingle(modelDto);
        return DepositMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }
}
