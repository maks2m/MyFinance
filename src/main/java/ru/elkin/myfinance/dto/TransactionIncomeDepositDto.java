package ru.elkin.myfinance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionIncomeDepositDto {
    private Long id;
    private Long money;
    private LocalDate date;
    private IncomeDto income;
    private DepositDto deposit;
}
