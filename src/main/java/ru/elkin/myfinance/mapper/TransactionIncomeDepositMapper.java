package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.TransactionIncomeDepositDto;
import ru.elkin.myfinance.entity.TransactionIncomeDeposit;

import java.util.List;

@Mapper
public interface TransactionIncomeDepositMapper {
    TransactionIncomeDepositMapper INSTANCE = Mappers.getMapper( TransactionIncomeDepositMapper.class );

    TransactionIncomeDepositDto mapSingle(TransactionIncomeDeposit item);
    @InheritInverseConfiguration
    TransactionIncomeDeposit mapSingle(TransactionIncomeDepositDto dto);

    List<TransactionIncomeDepositDto> mapList(List<TransactionIncomeDeposit> itemList);
    @InheritInverseConfiguration
    List<TransactionIncomeDeposit> mapListInvert(List<TransactionIncomeDepositDto> dtoList);
}
