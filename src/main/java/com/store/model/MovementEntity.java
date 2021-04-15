package com.store.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movements")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String number;
//    private StoreEntity toStore;
//    private StoreEntity fromStore;
//    private List<ProductEntity> products;
}
