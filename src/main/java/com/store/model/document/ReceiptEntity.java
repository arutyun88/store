package com.store.model.document;

import com.store.model.entity.StoreEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "receipts")
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @ManyToMany
    @JoinTable(
            name = "document_receipts_products",
            joinColumns = @JoinColumn(name = "id_receipt", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product_list", referencedColumnName = "id"))
    private List<ProductListEntity> products = new ArrayList<>();
}
