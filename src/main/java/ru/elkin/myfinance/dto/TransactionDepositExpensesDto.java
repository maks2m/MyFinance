package ru.elkin.myfinance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDepositExpensesDto {
    private Long id;
    private Long money;
    private LocalDate date;
    private DepositDto deposit;
    private ExpensesDto expenses;
}
