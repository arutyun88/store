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

    public void addReceiptProductList(List<ProductListEntity> productList) {
        for (ProductListEntity product : productList) {
            product.setPrice(product.getProduct().getLastPurchasePrice());
            entityManager.persist(product);
        }
    }

    public void addSaleProductList(List<ProductListEntity> productList) {
        for (ProductListEntity product : productList) {
            product.setPrice(product.getProduct().getLastSalePrice());
            entityManager.persist(product);
        }
    }
}
