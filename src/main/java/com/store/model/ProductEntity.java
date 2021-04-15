package com.store.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
