package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.TransactionDepositExpensesDto;
import ru.elkin.myfinance.entity.TransactionDepositExpenses;

import java.util.List;

@Mapper
public interface TransactionDepositExpensesMapper {
    TransactionDepositExpensesMapper INSTANCE = Mappers.getMapper( TransactionDepositExpensesMapper.class );

    TransactionDepositExpensesDto mapSingle(TransactionDepositExpenses item);
    @InheritInverseConfiguration
    TransactionDepositExpenses mapSingle(TransactionDepositExpensesDto dto);

    List<TransactionDepositExpensesDto> mapList(List<TransactionDepositExpenses> itemList);
    @InheritInverseConfiguration
    List<TransactionDepositExpenses> mapListInvert(List<TransactionDepositExpensesDto> DtoList);
}
