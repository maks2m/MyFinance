package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.elkin.myfinance.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username=:username")
    User findByUsername(String username);

    List<User> findAllByOrderById();

}
