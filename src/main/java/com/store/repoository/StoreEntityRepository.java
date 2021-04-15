package com.store.repoository;

import com.store.model.StoreEntity;
import com.store.model.dto.StatusResult;
import lombok.extern.java.Log;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
@Log
public class StoreEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public StatusResult addStore(StoreEntity storeEntity) {
        String storeName = storeEntity.getStoreName();
        Query query = entityManager.createQuery("from StoreEntity where storeName = : store_name");
        try {
            query.setParameter("store_name", storeName).getSingleResult();
            log.info("Склад не может быть создан.");
        } catch (NonUniqueResultException exception){
            log.info("Склад " + storeName + " зарегистрирован.");
            return StatusResult.FAILED_DOUBLE;
        } catch (NoResultException exception) {
            entityManager.persist(storeEntity);
            log.info("Склад " + storeName + " зарегистрирован.");
            return StatusResult.OK;
        }
        return StatusResult.FAILED_EXISTS;
    }

    public List<StoreEntity> getStores() {
        TypedQuery<StoreEntity> query = entityManager.createQuery("from StoreEntity", StoreEntity.class);
        return query.getResultList();
    }

    public void updateStore(StoreEntity storeEntity) {
        entityManager.merge(storeEntity);
    }

    public StoreEntity findById(long id) {
        Query query = entityManager.createQuery("from StoreEntity where id = : id");
        return (StoreEntity) query.setParameter("id", id).getSingleResult();
    }

    public void deleteStoreById(long id) {
        StoreEntity storeEntity = findById(id);
        entityManager.remove(storeEntity);
    }
}
