package com.store.controller;

import com.store.model.document.SaleEntity;
import com.store.service.SaleEntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("sale")
public class SaleEntityController {

    @Inject
    private SaleEntityService service;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<SaleEntity> getAllSales() {
        return service.getAllSales();
    }
}
