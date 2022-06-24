package ru.elkin.myfinance.repo;

import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.Role;

import java.util.List;

@Repository
public interface RoleRepo extends BaseRepository<Role> {

    List<Role> findAllByOrderById();
    Role findByRole(String role);

}
