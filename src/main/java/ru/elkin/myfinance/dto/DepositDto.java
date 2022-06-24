package ru.elkin.myfinance.dto;

import lombok.Data;

@Data
public class DepositDto extends AbstractDto {
    private Long id;
    private String name;
    private Long money;
}
