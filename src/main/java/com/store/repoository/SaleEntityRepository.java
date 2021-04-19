package com.store.repoository;

import com.store.model.document.ProductListEntity;
import com.store.model.document.ReceiptEntity;
import com.store.model.document.SaleEntity;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class SaleEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ProductEntityRepository productEntityRepository;

    @Inject
    private ProductListEntityRepository productListEntityRepository;

    public Response addSale(SaleEntity saleEntity) {
        for (ProductListEntity productListEntity : saleEntity.getProducts()) {
            productEntityRepository.changeLastSalePrice(
                    productListEntity.getProduct().getId(),
                    productListEntity.getProduct().getLastSalePrice());
        }
        productListEntityRepository.addSaleProductList(saleEntity.getProducts());
        entityManager.merge(saleEntity);
        return Response.ok().entity("Запись добавлена").build();
    }

    public List<SaleEntity> getAllSales() {
        TypedQuery<SaleEntity> query = entityManager.createQuery("from SaleEntity", SaleEntity.class);
        return query.getResultList();
    }

    public Response findSaleByNumber(String number) {
        Query query = entityManager.createQuery("from SaleEntity where number = : number");
        try {
            return Response.status(Response.Status.OK).entity(query
                    .setParameter("number", number)
                    .getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public Response findSaleById(long id) {
        Query query = entityManager.createQuery("from SaleEntity where id = : id");
        try {
            return Response.status(Response.Status.OK).entity(query
                    .setParameter("id", id)
                    .getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public Response deleteSaleById(long id) {
        try {
            SaleEntity storeEntity = (SaleEntity)  createQueryById(id).getSingleResult();
            entityManager.remove(storeEntity);
            return Response.ok().build();
        } catch (ClassCastException | NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private Query createQueryById(long id) {
        Query query = entityManager.createQuery("from SaleEntity where id = : id");
        return query.setParameter("id", id);
    }
}
