package com.store.repoository;

import com.store.model.ProductEntity;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ProductEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductEntity> getAllProducts() {
        TypedQuery<ProductEntity> query = entityManager.createQuery("from ProductEntity", ProductEntity.class);
        return query.getResultList();
    }

    public StatusResult addProduct(ProductEntity productEntity) {
        String article = productEntity.getArticle();
        String productName = productEntity.getProductName();
        Query queryArticle = entityManager
                .createQuery("from ProductEntity where article = : article");
        Query queryProductName = entityManager
                .createQuery("from ProductEntity where productName = : product_name");
        try {
            ProductEntity result = (ProductEntity) queryArticle
                    .setParameter("article", article)
                    .getSingleResult();
            if (result.getProductName().equals(productEntity.getProductName())) {
                productEntity.setId(result.getId());
                updateProduct(productEntity);
            } else return StatusResult.FAILED_INCOMPATIBILITY;

        } catch (NonUniqueResultException exception) {
            return StatusResult.FAILED_DOUBLE;
        } catch (NoResultException exception) {
            try {
                queryProductName.setParameter("product_name", productName).getSingleResult();
                return StatusResult.FAILED_INCOMPATIBILITY;
            } catch (NoResultException ex) {
                entityManager.persist(productEntity);
                return StatusResult.OK;
            } catch (NonUniqueResultException ex) {
                return StatusResult.FAILED_DOUBLE;
            }
        }
        return StatusResult.UPDATED;
    }

    public void updateProduct(ProductEntity productEntity) {
        entityManager.merge(productEntity);
    }
}
