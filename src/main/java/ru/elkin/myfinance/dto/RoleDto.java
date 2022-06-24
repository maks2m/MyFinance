package ru.elkin.myfinance.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private Long id;
    private String role;
    //private Set<UserDto> users;
}
