package com.store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "article")
    private String article;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "last_purchase_price")
    private double lastPurchasePrice;

    @Column(name = "last_sale_price")
    private double lastSalePrice;
}
