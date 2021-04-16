package com.store.service;

import com.store.model.document.SaleEntity;
import com.store.repoository.SaleEntityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SaleEntityService {

    @Inject
    private SaleEntityRepository repository;

    public List<SaleEntity> getAllSales() {
        return repository.getAllSales();
    }
}
