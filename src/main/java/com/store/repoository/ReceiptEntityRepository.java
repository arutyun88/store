package com.store.repoository;

import com.store.model.document.ProductListEntity;
import com.store.model.document.ReceiptEntity;
import com.store.model.dto.ErrorMessage;
import com.store.model.entity.ProductEntity;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.util.InfoResult.*;

@Stateless
public class ReceiptEntityRepository {

    @Inject
    private ProductListEntityRepository productListEntityRepository;

    @Inject
    private ProductEntityRepository productEntityRepository;

    @Inject
    private StoreEntityRepository storeEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ReceiptEntity> getAllReceipts() {
        TypedQuery<ReceiptEntity> query = entityManager.createQuery("from ReceiptEntity", ReceiptEntity.class);
        return query.getResultList();
    }

    public Response addReceipt(ReceiptEntity receiptEntity) {
        for (ProductListEntity productListEntity : receiptEntity.getProducts()) {
            productEntityRepository.changeLastPurchasePrice(
                    productListEntity.getProduct().getId(),
                    productListEntity.getProduct().getLastPurchasePrice());
        }
        productListEntityRepository.addProductList(receiptEntity.getProducts());
        entityManager.merge(receiptEntity);
        return Response.ok().entity("Запись добавлена").build();
    }

    public Response findReceiptByNumber(String number) {
        Query query = entityManager.createQuery("from ReceiptEntity where number = : number");
        try {
            return Response.status(Response.Status.OK).entity(query
                    .setParameter("number", number)
                    .getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }
}
