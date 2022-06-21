package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elkin.myfinance.entity.Role;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    List<Role> findAllByOrderById();
    Role findByRole(String role);
}
