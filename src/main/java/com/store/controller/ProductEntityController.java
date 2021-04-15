package com.store.controller;

import com.store.model.ProductEntity;
import com.store.repoository.ProductEntityRepository;
import com.store.util.StatusResult;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.service.ErrorResponseService.getErrorResponse;
import static com.store.util.InfoResult.OK;
import static com.store.util.InfoResult.UPDATED_MESSAGE;

@Path("product")
public class ProductEntityController {

    @Inject
    ProductEntityRepository productEntityRepository;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductEntity> getAllProducts() {
        return productEntityRepository.getAllProducts();
    }

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductEntity productEntity) {
        StatusResult statusResult = productEntityRepository.addProduct(productEntity);
        if (StatusResult.OK.equals(statusResult)) return Response.status(OK).build();
        else if (StatusResult.UPDATED.equals(statusResult)) return Response.status(OK).entity(UPDATED_MESSAGE).build();
        else return getErrorResponse(statusResult);
    }
}
