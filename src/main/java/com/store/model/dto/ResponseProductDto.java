package com.store.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseProductDto {
    private long id;
    private String number;
    private String store;
    private List<ProductDto> products;
}
