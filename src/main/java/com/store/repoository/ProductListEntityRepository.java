package com.store.repoository;

import com.store.model.document.ProductListEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public void deleteById(long id) {
        ProductListEntity storeEntity = (ProductListEntity)  createQueryById(id).getSingleResult();
        entityManager.remove(storeEntity);
    }

    private Query createQueryById(long id) {
        Query query = entityManager.createQuery("from ProductListEntity where id = : id");
        return query.setParameter("id", id);
    }
}
