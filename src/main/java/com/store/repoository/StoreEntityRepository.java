package com.store.repoository;

import com.store.model.StoreEntity;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class StoreEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public StatusResult addStore(StoreEntity storeEntity) {
        String storeName = storeEntity.getStoreName();
        Query query = entityManager.createQuery("from StoreEntity where storeName = : store_name");
        try {
            query.setParameter("store_name", storeName).getSingleResult();
        } catch (NonUniqueResultException exception){
            return StatusResult.FAILED_DOUBLE;
        } catch (NoResultException exception) {
            entityManager.persist(storeEntity);
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

    public Response deleteStoreById(long id) {
        try {
            StoreEntity storeEntity = findById(id);
            entityManager.remove(storeEntity);
            return Response.ok().build();
        } catch (ClassCastException | NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
