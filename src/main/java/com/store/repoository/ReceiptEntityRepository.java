package com.store.repoository;

import com.store.model.document.ReceiptEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ReceiptEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ReceiptEntity> getAllReceipts() {
        TypedQuery<ReceiptEntity> query = entityManager.createQuery("from ReceiptEntity", ReceiptEntity.class);
        return query.getResultList();
    }

    public void addReceipt(ReceiptEntity receiptEntity) {
        entityManager.persist(receiptEntity);
    }
}
