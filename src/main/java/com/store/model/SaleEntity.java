package com.store.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
