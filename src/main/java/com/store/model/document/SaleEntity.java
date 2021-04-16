package com.store.model.document;

import com.store.model.entity.StoreEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sales")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @ManyToMany
    @JoinTable(
            name = "document_sale_products",
            joinColumns = @JoinColumn(name = "id_sale", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product_list", referencedColumnName = "id"))
    private List<ProductListEntity> products;
}
