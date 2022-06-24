package ru.elkin.myfinance.repo;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ru.elkin.myfinance.entity.AbstractEntity;
import ru.elkin.myfinance.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class BaseRepositoryImpl <T extends AbstractEntity> extends SimpleJpaRepository<T, Long> implements BaseRepository<T> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                              EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findUserByUsername(String username) {
        return entityManager
                .createQuery("select u from User u join fetch u.roles where u.username=:username", getDomainClass())
                .setParameter("username", username)
                .getResultStream().findAny();
    }

    @Override
    public T findByIdAndUser(Long id, User user) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
        Root<T> root = criteriaQuery.from(getDomainClass());
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("id"), id),
                criteriaBuilder.equal(root.get("user"), user));
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }


}
