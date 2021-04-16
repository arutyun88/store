package com.store.controller;

import com.store.model.entity.StoreEntity;
import com.store.service.StoreEntityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("store")
public class StoreEntityController {

    @Inject
    private StoreEntityService service;

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStore(StoreEntity storeEntity) {
        return service.addStore(storeEntity);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<StoreEntity> getStores() {
        return service.getAllStores();
    }

    @GET
    @Path("get/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreById(@PathParam("id") long id) {
        return service.getStoreById(id);
    }

    @GET
    @Path("get/name/{storeName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreByName(@PathParam("storeName") String storeName) {
        return service.getStoreByName(storeName);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteStoreEntity(@PathParam("id") long id) {
        return service.deleteStoreById(id);
    }

    @POST
    @Path("update")
    @Produces("application/json")
    public Response updateStoreEntity(StoreEntity entity) {
        return service.updateStore(entity);
    }
}
