package com.store.repoository;

import com.store.model.ProductEntity;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.core.Response;
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

    public Response findProductById(long id) {
        Query query = entityManager.createQuery("from ProductEntity where id = : id");
        try {
            return Response.status(Response.Status.OK).entity(query.setParameter("id", id).getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public List<ProductEntity> checkProduct(ProductEntity productEntity) {
        String article = productEntity.getArticle();
        String productName = productEntity.getProductName();
        long id = productEntity.getId();
        TypedQuery<ProductEntity> query = entityManager.createQuery(
                "from ProductEntity where (article = : article or productName = : product_name) and id != : id",
                ProductEntity.class);
        return query
                .setParameter("product_name", productName)
                .setParameter("article", article)
                .setParameter("id", id)
                .getResultList();
    }

    public Response findProductByproductName(String productName) {
        Query query = entityManager.createQuery("from ProductEntity where productName = : product_name");
        try {
            return Response.status(Response.Status.OK).entity(query
                    .setParameter("product_name", productName)
                    .getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public Response deleteProductById(long id) {
        Response response = findProductById(id);
        try {
            ProductEntity productEntity = (ProductEntity) response.getEntity();
            entityManager.remove(productEntity);
            return Response.ok().build();
        } catch (ClassCastException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
