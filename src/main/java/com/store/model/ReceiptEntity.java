package com.store.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "receipts")
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String number;
//    private StoreEntity store;
//    private List<ProductEntity> products;
}
