package com.store.repoository;

import com.store.model.document.SaleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SaleEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addSale(SaleEntity saleEntity) {
        entityManager.persist(saleEntity);
    }
}
