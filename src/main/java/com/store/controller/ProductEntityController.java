package com.store.controller;

import com.store.model.entity.ProductEntity;
import com.store.service.ProductEntityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("product")
public class ProductEntityController {

    @Inject
    ProductEntityService service;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductEntity> getAllProducts() {
        return service.getAllProducts();
    }

    @GET
    @Path("get/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        return service.getProductById(id);
    }

    @GET
    @Path("get/name/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByName(@PathParam("productName") String productName) {
        return service.getProductByName(productName);
    }

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductEntity productEntity) {
        return service.addProduct(productEntity);
    }

    @POST
    @Path("update")
    @Produces("application/json")
    public Response updateStoreEntity(ProductEntity productEntity) {
        return service.updateStore(productEntity);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteProductEntity(@PathParam("id") long id) {
        return service.deleteProductById(id);
    }
}
