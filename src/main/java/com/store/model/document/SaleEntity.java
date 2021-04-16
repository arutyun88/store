package com.store.model.document;

import com.store.model.entity.ProductEntity;
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
            name = "sale_products_table",
            joinColumns = @JoinColumn(name = "id_sale", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"))
    private List<ProductEntity> products;
}
