package com.store.repoository;

import com.store.model.document.ProductListEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductListEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addProductList(List<ProductListEntity> productList) {
        for (ProductListEntity product : productList) {
            entityManager.persist(product);
        }
    }
}
