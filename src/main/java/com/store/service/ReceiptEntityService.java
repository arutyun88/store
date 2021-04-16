package com.store.service;

import com.store.model.document.ReceiptEntity;
import com.store.repoository.ReceiptEntityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ReceiptEntityService {

    @Inject
    private ReceiptEntityRepository receiptEntityRepository;

    public List<ReceiptEntity> getAllReceipts() {
        return receiptEntityRepository.getAllReceipts();
    }

    public Response addReceipt(ReceiptEntity receiptEntity) {
        return receiptEntityRepository.addReceipt(receiptEntity);
    }
}
