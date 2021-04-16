package com.store.repoository;

import com.store.model.document.SaleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SaleEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addSale(SaleEntity saleEntity) {
        entityManager.persist(saleEntity);
    }

    public List<SaleEntity> getAllSales() {
        TypedQuery<SaleEntity> query = entityManager.createQuery("from SaleEntity", SaleEntity.class);
        return query.getResultList();
    }
}
