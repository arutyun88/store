package com.store.repoository;

import com.store.model.entity.StoreEntity;
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

    public Response deleteStoreById(long id) {
        try {
            StoreEntity storeEntity = (StoreEntity)  createQueryById(id).getSingleResult();
            entityManager.remove(storeEntity);
            return Response.ok().build();
        } catch (ClassCastException | NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response findStoreById(long id) {
        try {
            return Response.status(Response.Status.OK).entity(createQueryById(id).getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public Response findStoreByName(String storeName) {
        Query query = entityManager.createQuery("from StoreEntity where storeName = : store_name");
        try {
            return Response.status(Response.Status.OK).entity(query
                    .setParameter("store_name", storeName)
                    .getSingleResult()).build();
        } catch (NoResultException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(StatusResult.NOT_FOUND).build();
        }
    }

    public List<StoreEntity> checkStore(StoreEntity storeEntity) {
        String storeName = storeEntity.getStoreName();
        TypedQuery<StoreEntity> query = entityManager.createQuery(
                "from StoreEntity where storeName = : store_name",
                StoreEntity.class);
        return query
                .setParameter("store_name", storeName)
                .getResultList();
    }

    private Query createQueryById(long id) {
        Query query = entityManager.createQuery("from StoreEntity where id = : id");
        return query.setParameter("id", id);
    }
}
