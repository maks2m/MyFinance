package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.ExpensesDto;
import ru.elkin.myfinance.entity.Expenses;

import java.util.List;

@Mapper
public interface ExpensesMapper {
    ExpensesMapper INSTANCE = Mappers.getMapper( ExpensesMapper.class );

    ExpensesDto mapSingle(Expenses item);
    @InheritInverseConfiguration
    Expenses mapSingle(ExpensesDto dto);

    List<ExpensesDto> mapList(List<Expenses> itemList);
    @InheritInverseConfiguration
    List<Expenses> mapListInvert(List<ExpensesDto> dtoList);
}
