package com.store.service;

import com.store.model.document.MovementEntity;
import com.store.repoository.MovementEntityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MovementEntityService {

    @Inject
    private MovementEntityRepository repository;

    public List<MovementEntity> getAllMovements() {
        return repository.getAllMovements();
    }
}
