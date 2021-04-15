package com.store.repoository;

import com.store.model.StoreEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StoreEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addStore(StoreEntity storeEntity) {
        entityManager.persist(storeEntity);
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
