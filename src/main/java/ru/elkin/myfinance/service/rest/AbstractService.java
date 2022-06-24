package ru.elkin.myfinance.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import ru.elkin.myfinance.entity.AbstractEntity;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.BaseRepository;

public abstract class AbstractService<E extends AbstractEntity, R extends BaseRepository<E>>
        implements RestService<E> {

    protected final R repository;

        @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E getOne(Long id, User user) {
        return repository.findByIdAndUser(id, user);
    }

    @Override
    public void delete(Long id, User user) {
        repository.deleteById(id);
    }
}
