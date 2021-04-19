package com.store.util;

import com.store.model.document.ProductListEntity;
import com.store.model.document.ReceiptEntity;
import com.store.model.document.SaleEntity;
import com.store.model.dto.ErrorMessage;
import com.store.model.dto.ProductDto;
import com.store.model.dto.ResponseDocumentDto;
import com.store.model.entity.StoreEntity;
import com.store.service.ProductDtoService;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static com.store.util.InfoResult.*;

public class ResponseService {

    public static Response getResponse(Response response) {
        if (response.getStatus() == OK) {
            return response;
        } else if (response.getStatus() == NOT_FOUND) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        } else return Response.status(FATAL_ERROR).entity(
                ErrorMessage.builder()
                        .code(FATAL_ERROR)
                        .message(FATAL_ERROR_MESSAGE)
                        .build())
                .build();
    }

    public static Response getErrorResponse(StatusResult statusResult) {
        if (StatusResult.FAILED_DOUBLE.equals(statusResult)) {
            return Response.status(FAILED_DOUBLE).entity(
                    ErrorMessage.builder()
                            .code(FAILED_DOUBLE)
                            .message(FAILED_DOUBLE_MESSAGE)
                            .build())
                    .build();
        } else if (StatusResult.FAILED_INCOMPATIBILITY.equals(statusResult)) {
            return Response.status(FAILED_INCOMPATIBILITY).entity(
                    ErrorMessage.builder()
                            .code(FAILED_INCOMPATIBILITY)
                            .message(FAILED_INCOMPATIBILITY_MESSAGE)
                            .build())
                    .build();
        } else if (StatusResult.FAILED_EXISTS.equals(statusResult)) {
            return Response.status(FAILED_EXISTS).entity(
                    ErrorMessage.builder()
                            .code(FAILED_EXISTS)
                            .message(FAILED_EXISTS_MESSAGE)
                            .build())
                    .build();
        } else {
            return Response.status(FATAL_ERROR).entity(
                    ErrorMessage.builder()
                            .code(FATAL_ERROR)
                            .message(FATAL_ERROR_MESSAGE)
                            .build())
                    .build();
        }
    }

    public static Response getResponseSale(Response response) {
        if (getResponse(response).getStatus() == OK) {
            SaleEntity saleEntity = (SaleEntity) response.getEntity();
            return getProductDto(saleEntity.getProducts(), saleEntity.getId(), saleEntity.getNumber(), saleEntity.getStore());
        }
        return getResponse(response);
    }


    public static Response getResponseReceipt(Response response) {
        if (getResponse(response).getStatus() == OK) {
            ReceiptEntity receiptEntity = (ReceiptEntity) response.getEntity();
            return getProductDto(receiptEntity.getProducts(), receiptEntity.getId(), receiptEntity.getNumber(), receiptEntity.getStore());
        }
        return getResponse(response);
    }

    private static Response getProductDto(List<ProductListEntity> products, long id, String number, StoreEntity store) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductListEntity productListEntity : products) {
            productDtoList.add(ProductDtoService.mappingSaleEntityToProductDto(
                    productListEntity.getProduct(),
                    productListEntity.getCount(),
                    productListEntity.getPrice()));
        }
        return Response.status(OK).entity(
                ResponseDocumentDto.builder()
                        .id(id)
                        .number(number)
                        .store(store.getStoreName())
                        .products(productDtoList)
                        .build())
                .build();
    }
}
