package com.store.repoository;

import com.store.model.document.MovementEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MovementEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addMovement(MovementEntity movementEntity) {
        entityManager.persist(movementEntity);
    }

    public List<MovementEntity> getAllMovements() {
        TypedQuery<MovementEntity> query = entityManager.createQuery("from MovementEntity", MovementEntity.class);
        return query.getResultList();
    }
}
