package com.store.repoository;

import com.store.model.document.ReceiptEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReceiptEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addReceipt(ReceiptEntity receiptEntity) {
        entityManager.persist(receiptEntity);
    }
}
