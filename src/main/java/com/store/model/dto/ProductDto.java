package com.store.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private long id;
    private String name;
    private double price;
    private int count;
}
