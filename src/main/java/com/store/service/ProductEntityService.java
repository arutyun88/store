package com.store.service;

import com.store.model.dto.ErrorMessage;
import com.store.model.entity.ProductEntity;
import com.store.repoository.ProductEntityRepository;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.util.InfoResult.*;
import static com.store.util.ResponseService.getErrorResponse;
import static com.store.util.ResponseService.getResponse;

@Stateless
public class ProductEntityService {

    @Inject
    ProductEntityRepository productEntityRepository;

    public List<ProductEntity> getAllProducts() {
        return productEntityRepository.getAllProducts();
    }

    public Response getProductById(long id) {
        Response response = productEntityRepository.findProductById(id);
        return getResponse(response);
    }

    public Response getProductByName(String productName) {
        Response response = productEntityRepository.findProductByproductName(productName);
        return getResponse(response);
    }

    public Response addProduct(ProductEntity productEntity) {
        StatusResult statusResult = productEntityRepository.addProduct(productEntity);
        if (StatusResult.OK.equals(statusResult)) return Response.status(OK).build();
        else if (StatusResult.UPDATED.equals(statusResult)) return Response.status(OK).entity(UPDATED_MESSAGE).build();
        else return getErrorResponse(statusResult);
    }

    public Response updateStore(ProductEntity productEntity) {
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

    public Response deleteProductById(long id) {
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
