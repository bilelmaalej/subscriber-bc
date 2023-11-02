package com.canalplus.subscriber.repositories;

import com.canalplus.subscriber.models.Subscriber;
import com.canalplus.subscriber.models.SubscriberCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepositoryCustom {

private final EntityManager entityManager;

    public SubscriberRepositoryImpl(JpaContext jpaContext) {
        this.entityManager = jpaContext.getEntityManagerByManagedType(Subscriber.class);
    }

    @Override
    public List<Subscriber> findByCriteria(SubscriberCriteria subscriberCriteria) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscriber> criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
        Root<Subscriber> subscriberRoot = criteriaQuery.from(Subscriber.class);
        criteriaQuery.select(subscriberRoot);

        List<Predicate> criteriaPredicateList = new ArrayList<>();
        if (subscriberCriteria.getId() != null) {
            criteriaPredicateList.add(criteriaBuilder.equal(subscriberRoot.get("id"), subscriberCriteria.getId()));
        }

        if (subscriberCriteria.getFname() != null) {
            criteriaPredicateList.add(criteriaBuilder.equal(subscriberRoot.get("fname"), subscriberCriteria.getFname()));
        }

        if (subscriberCriteria.getLname() != null) {
            criteriaPredicateList.add(criteriaBuilder.equal(subscriberRoot.get("lname"), subscriberCriteria.getLname()));
        }

        if (subscriberCriteria.getMail() != null) {
            criteriaPredicateList.add(criteriaBuilder.equal(subscriberRoot.get("mail"), subscriberCriteria.getMail()));
        }

        if (subscriberCriteria.getPhone() != null) {
            criteriaPredicateList.add(criteriaBuilder.equal(subscriberRoot.get("phone"), subscriberCriteria.getPhone()));
        }

        criteriaQuery.where(criteriaPredicateList.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
