package com.store.controller;

import com.store.model.entity.ProductEntity;
import com.store.model.dto.ErrorMessage;
import com.store.repoository.ProductEntityRepository;
import com.store.util.StatusResult;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.service.ResponseService.getErrorResponse;
import static com.store.service.ResponseService.getResponse;
import static com.store.util.InfoResult.*;

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

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        Response response = productEntityRepository.findProductById(id);
        return getResponse(response);
    }

    @GET
    @Path("get/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByName(@PathParam("productName") String productName) {
        Response response = productEntityRepository.findProductByproductName(productName);
        return getResponse(response);
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

    @POST
    @Path("update")
    @Produces("application/json")
    public Response updateStoreEntity(ProductEntity productEntity) {
        try {
            if (productEntityRepository.checkProduct(productEntity).size() != 0) {
                return Response.status(FAILED_DOUBLE).entity(
                        ErrorMessage.builder()
                                .code(FAILED_DOUBLE)
                                .message(FAILED_DOUBLE_MESSAGE)
                                .build())
                        .build();
            }
            productEntityRepository.updateProduct(productEntity);
            return Response.status(OK).build();
        } catch (NoResultException exception) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        } catch (Exception exception) {
            return Response.status(FATAL_ERROR).entity(
                    ErrorMessage.builder()
                            .code(FATAL_ERROR)
                            .message(FATAL_ERROR_MESSAGE)
                            .build())
                    .build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteProductEntity(@PathParam("id") long id) {
            Response response = productEntityRepository.deleteProductById(id);
            if (response.getStatus() == OK) {
                return Response.status(OK).build();
            }
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
    }
}
