package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.IncomeDto;
import ru.elkin.myfinance.entity.Income;

import java.util.List;

@Mapper
public interface IncomeMapper {
    IncomeMapper INSTANCE = Mappers.getMapper( IncomeMapper.class );

    IncomeDto mapSingle(Income item);
    @InheritInverseConfiguration
    Income mapSingle(IncomeDto dto);

    List<IncomeDto> mapList(List<Income> itemList);
    @InheritInverseConfiguration
    List<Income> mapListInvert(List<IncomeDto> dtoList);
}
