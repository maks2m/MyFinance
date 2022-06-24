package ru.elkin.myfinance.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;
    private String fullName;
    private Set<RoleDto> roles = new HashSet<>();
}
