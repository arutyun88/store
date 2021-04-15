package com.store.repoository;

import com.store.model.document.MovementEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MovementEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addMovement(MovementEntity movementEntity) {
        entityManager.persist(movementEntity);
    }
}
