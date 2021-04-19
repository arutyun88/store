package com.store.controller;

import com.store.model.document.ReceiptEntity;
import com.store.model.dto.ResponseProductDto;
import com.store.service.ReceiptEntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public List<ResponseProductDto> getAllReceipts() {
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
}
