package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.IncomeDto;
import ru.elkin.myfinance.entity.Income;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.IncomeMapper;
import ru.elkin.myfinance.service.rest.IncomeService;

import java.util.List;

@RestController
@RequestMapping("/rest/income")
public class IncomeRestController extends AbstractRestController<IncomeDto, Income, IncomeService>{
    protected IncomeRestController(IncomeService service) {
        super(service);
    }

    @Override
    public List<IncomeDto> index(User user) {
        return IncomeMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public IncomeDto getOne(Long id, User user) {
        return IncomeMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public IncomeDto create(IncomeDto modelDto, User user) {
        Income model = IncomeMapper.INSTANCE.mapSingle(modelDto);
        return IncomeMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public IncomeDto update(Long id, IncomeDto modelDto, User user) {
        Income model = IncomeMapper.INSTANCE.mapSingle(modelDto);
        return IncomeMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }
}
