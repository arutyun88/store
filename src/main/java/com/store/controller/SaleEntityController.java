package com.store.controller;

import com.store.model.document.SaleEntity;
import com.store.service.SaleEntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.util.InfoResult.OK;

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

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSale(SaleEntity saleEntity) {
        Response response = service.addSale(saleEntity);
        if (response.getStatus() == OK) {
            return Response.status(OK).build();
        }
        return response;
    }
}
