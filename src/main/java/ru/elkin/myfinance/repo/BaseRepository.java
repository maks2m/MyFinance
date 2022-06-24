package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.elkin.myfinance.entity.AbstractEntity;
import ru.elkin.myfinance.entity.User;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <E extends AbstractEntity> extends JpaRepository<E, Long> {

    Optional<E> findUserByUsername(String username);

    E findByIdAndUser(Long id, User user);

}
