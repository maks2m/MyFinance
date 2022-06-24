package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elkin.myfinance.entity.Deposit;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Repository
public interface DepositRepo extends BaseRepository<Deposit> {

    List<Deposit> findAllByUserOrderById(User user);

    Deposit findByIdAndUser(Long id, User user);

}
