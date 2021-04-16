package com.store.model.document;

import com.store.model.entity.StoreEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "movements")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "to_store_id")
    private StoreEntity toStore;

    @ManyToOne
    @JoinColumn(name = "from_store_id")
    private StoreEntity fromStore;

    @ManyToMany
    @JoinTable(
            name = "document_movement_products",
            joinColumns = @JoinColumn(name = "id_movement", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product_list", referencedColumnName = "id"))
    private List<ProductListEntity> products;
}
