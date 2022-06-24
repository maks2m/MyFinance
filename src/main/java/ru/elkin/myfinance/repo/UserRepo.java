package ru.elkin.myfinance.repo;

import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface UserRepo extends BaseRepository<User> {

    List<User> findAllByOrderById();

}
