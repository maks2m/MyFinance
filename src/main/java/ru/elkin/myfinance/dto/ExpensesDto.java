package ru.elkin.myfinance.dto;

import lombok.Data;

@Data
public class ExpensesDto {
    private Long id;
    private String name;
    private Long planMoney;
    private Long factMoney;
}
