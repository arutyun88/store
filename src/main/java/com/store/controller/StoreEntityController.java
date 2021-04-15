package com.store.controller;

import com.store.model.StoreEntity;
import com.store.model.dto.ErrorMessage;
import com.store.util.StatusResult;
import com.store.repoository.StoreEntityRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.store.service.ResponseService.getErrorResponse;
import static com.store.util.InfoResult.*;

@Path("store")
public class StoreEntityController {

    @Inject
    private StoreEntityRepository storeEntityRepository;

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStore(StoreEntity storeEntity) {
        StatusResult statusResult = storeEntityRepository.addStore(storeEntity);
        if (statusResult.equals(StatusResult.OK)) return Response.status(OK).build();
        else return getErrorResponse(statusResult);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<StoreEntity> getStores() {
        return storeEntityRepository.getStores();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteStoreEntity(@PathParam("id") long id) {
        Response response = storeEntityRepository.deleteStoreById(id);
        if (response.getStatus() == OK) {
            return Response.status(OK).build();
        }
        return Response.status(NOT_FOUND).entity(
                ErrorMessage.builder()
                        .code(NOT_FOUND)
                        .message(NOT_FOUND_MESSAGE)
                        .build())
                .build();
    }

    @POST
    @Path("update")
    @Consumes("application/json")
    public void updateStoreEntity(StoreEntity entity) {
        storeEntityRepository.updateStore(entity);
    }
}
