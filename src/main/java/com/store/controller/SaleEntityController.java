package com.store.controller;

import com.store.model.document.SaleEntity;
import com.store.model.dto.ResponseDocumentDto;
import com.store.service.SaleEntityService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public List<ResponseDocumentDto> getAllSales() {
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

    @GET
    @Path("get/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSaleById(@PathParam("id") long id) {
        return service.getSaleById(id);
    }

    @GET
    @Path("get/number/{saleNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSaleBySaleNumber(@PathParam("saleNumber") String saleNumber) {
        return service.getSaleBySaleNumber(saleNumber);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteSaleEntity(@PathParam("id") long id) {
        return service.deleteSaleById(id);
    }
}
