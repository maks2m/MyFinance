package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.DepositDto;
import ru.elkin.myfinance.entity.Deposit;

import java.util.List;

@Mapper
public interface DepositMapper {

    DepositMapper INSTANCE = Mappers.getMapper( DepositMapper.class );

    DepositDto mapSingle(Deposit item);
    @InheritInverseConfiguration
    Deposit mapSingle(DepositDto dto);

    List<DepositDto> mapList(List<Deposit> itemList);
    @InheritInverseConfiguration
    List<Deposit> mapListInvert(List<DepositDto> dtoList);
}
