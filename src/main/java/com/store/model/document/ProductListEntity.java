package com.store.model.document;

import com.store.model.entity.ProductEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_list")
public class ProductListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private double price;

}
