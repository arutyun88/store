package com.store.service;

import com.store.model.dto.ProductDto;
import com.store.model.entity.ProductEntity;

public class ProductDtoService {
    public static ProductDto mappingReceiptEntityToProductDto(
            ProductEntity productEntity,
            int count,
            double price) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getProductName())
                .price(price)
                .count(count)
                .build();
    }

    public static ProductDto mappingSaleEntityToProductDto(
            ProductEntity productEntity,
            int count,
            double price) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getProductName())
                .price(price)
                .count(count)
                .build();
    }
}
