package com.store.repoository;

import com.store.model.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addProduct(ProductEntity productEntity) {
        entityManager.persist(productEntity);
    }
}
