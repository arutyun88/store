package com.store.service;

import com.store.model.document.ProductListEntity;
import com.store.model.document.SaleEntity;
import com.store.model.dto.ErrorMessage;
import com.store.model.dto.ProductDto;
import com.store.model.dto.ResponseProductDto;
import com.store.repoository.ProductEntityRepository;
import com.store.repoository.SaleEntityRepository;
import com.store.repoository.StoreEntityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.store.util.InfoResult.*;

@Stateless
public class SaleEntityService {

    @Inject
    private SaleEntityRepository repository;

    @Inject
    private StoreEntityRepository storeEntityRepository;

    @Inject
    private ProductEntityRepository productEntityRepository;

    public List<ResponseProductDto> getAllSales() {

        List<SaleEntity> receiptEntityList = repository.getAllSales();

        List<ResponseProductDto> responseProductDtoList = new ArrayList<>();

        for (SaleEntity receiptEntity : receiptEntityList) {
            List<ProductDto> productDtoList = new ArrayList<>();
            for (ProductListEntity productListEntity : receiptEntity.getProducts()) {
                productDtoList.add(ProductDtoService.mappingSaleEntityToProductDto(
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

//        return repository.getAllSales();
    }

    public Response addSale(SaleEntity saleEntity) {
        if (repository.findSaleByNumber(saleEntity.getNumber()).getStatus() == OK) {
            return Response.status(FAILED_EXISTS).entity(
                    ErrorMessage.builder()
                            .code(FAILED_EXISTS)
                            .message(FAILED_DOUBLE_MESSAGE)
                            .build())
                    .build();
        }
        Response responseStore = storeEntityRepository.findStoreById(saleEntity.getStore().getId());
        if (responseStore.getStatus() != OK) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        }
        for (ProductListEntity productListEntity : saleEntity.getProducts()) {
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

        return repository.addSale(saleEntity);
    }
}
