package com.store.controller;

import com.store.model.document.ReceiptEntity;
import com.store.model.dto.ResponseDocumentDto;
import com.store.service.ReceiptEntityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.util.InfoResult.OK;

@Path("receipt")
public class ReceiptEntityController {

    @Inject
    private ReceiptEntityService service;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<ResponseDocumentDto> getAllReceipts() {
        return service.getAllReceipts();
    }

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReceipt(ReceiptEntity receiptEntity) {
        Response response = service.addReceipt(receiptEntity);
        if (response.getStatus() == OK) {
            return Response.status(OK).build();
        }
        return response;
    }

    @GET
    @Path("get/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceiptById(@PathParam("id") long id) {
        return service.getReceiptById(id);
    }

    @GET
    @Path("get/number/{receiptNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceiptByReceiptNumber(@PathParam("receiptNumber") String receiptNumber) {
        return service.getReceiptByReceiptNumber(receiptNumber);
    }
}
