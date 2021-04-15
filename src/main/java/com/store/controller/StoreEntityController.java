package com.store.controller;

import com.store.model.StoreEntity;
import com.store.repoository.StoreEntityRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("store")
public class StoreEntityController {

    @Inject
    private StoreEntityRepository storeEntityRepository;

    @PUT
    @Path("add")
    @Consumes("application/json")
    public void addStore(StoreEntity storeEntity) {
        storeEntityRepository.addStore(storeEntity);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<StoreEntity> getStores() {
        return storeEntityRepository.getStores();
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes("application/json")
    public void deleteStoreEntity(@PathParam("id") long id) {
        storeEntityRepository.deleteStoreById(id);
    }

    @POST
    @Path("update")
    @Consumes("application/json")
    public void updateStoreEntity(StoreEntity entity) {
        storeEntityRepository.updateStore(entity);
    }
}
