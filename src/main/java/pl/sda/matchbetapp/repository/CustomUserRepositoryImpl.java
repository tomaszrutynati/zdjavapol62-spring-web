package pl.sda.matchbetapp.repository;

import org.springframework.stereotype.Repository;
import pl.sda.matchbetapp.api.model.UserSearchParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> searchByParams(UserSearchParams searchParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (searchParams.getFirstName() != null && !searchParams.getFirstName().isEmpty()) {
            predicates.add(cb.equal(root.get("firstName"), searchParams.getFirstName()));
        }
        if (searchParams.getLastName() != null && !searchParams.getLastName().isEmpty()) {
            predicates.add(cb.equal(root.get("lastName"), searchParams.getLastName()));
        }
        if (searchParams.getLogin() != null && !searchParams.getLogin().isEmpty()) {
            if (searchParams.isExactMatch()) {
                predicates.add(cb.equal(root.get("login"), searchParams.getLogin()));
            } else {
                predicates.add(cb.like(root.get("login"), "%" + searchParams.getLogin() + "%" ));
            }
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}
