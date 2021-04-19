package com.store.service;

import com.store.model.document.ProductListEntity;
import com.store.model.document.ReceiptEntity;
import com.store.model.dto.ErrorMessage;
import com.store.model.dto.ProductDto;
import com.store.model.dto.ResponseProductDto;
import com.store.repoository.ProductEntityRepository;
import com.store.repoository.ReceiptEntityRepository;
import com.store.repoository.StoreEntityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.store.util.InfoResult.*;

@Stateless
public class ReceiptEntityService {

    @Inject
    private ReceiptEntityRepository receiptEntityRepository;

    @Inject
    private ProductEntityRepository productEntityRepository;

    @Inject
    private StoreEntityRepository storeEntityRepository;

    public List<ResponseProductDto> getAllReceipts() {
        List<ReceiptEntity> receiptEntityList = receiptEntityRepository.getAllReceipts();

        List<ResponseProductDto> responseProductDtoList = new ArrayList<>();

        for (ReceiptEntity receiptEntity : receiptEntityList) {
            List<ProductDto> productDtoList = new ArrayList<>();
            for (ProductListEntity productListEntity : receiptEntity.getProducts()) {
                productDtoList.add(ProductDtoService.mappingReceiptEntityToProductDto(
                        productListEntity.getProduct(),
                        productListEntity.getCount(),
                        productListEntity.getPrice()));
            }

            responseProductDtoList.add(ResponseProductDto.builder()
                    .id(receiptEntity.getId())
                    .number(receiptEntity.getNumber())
                    .store(receiptEntity.getStore().getStoreName())
                    .products(productDtoList)
                    .build());
        }
        return responseProductDtoList;
    }

    public Response addReceipt(ReceiptEntity receiptEntity) {
        if (receiptEntityRepository.findReceiptByNumber(receiptEntity.getNumber()).getStatus() == OK) {
            return Response.status(FAILED_EXISTS).entity(
                    ErrorMessage.builder()
                            .code(FAILED_EXISTS)
                            .message(FAILED_DOUBLE_MESSAGE)
                            .build())
                    .build();
        }
        Response responseStore = storeEntityRepository.findStoreById(receiptEntity.getStore().getId());
        if (responseStore.getStatus() != OK) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        }
        for (ProductListEntity productListEntity : receiptEntity.getProducts()) {
            Response responseProduct =
                    productEntityRepository.findProductById(productListEntity.getProduct().getId());
            if (responseProduct.getStatus() != OK) {
                return Response.status(NOT_FOUND).entity(
                        ErrorMessage.builder()
                                .code(NOT_FOUND)
                                .message(NOT_FOUND_MESSAGE)
                                .build())
                        .build();
            }
        }
        return receiptEntityRepository.addReceipt(receiptEntity);
    }
}
