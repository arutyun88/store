package com.store.controller;

import com.store.model.StoreEntity;
import com.store.model.dto.ErrorMessage;
import com.store.model.dto.StatusResult;
import com.store.repoository.StoreEntityRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("store")
public class StoreEntityController {

    @Inject
    private StoreEntityRepository storeEntityRepository;

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStore(StoreEntity storeEntity) {
        StatusResult statusResult;
        statusResult = storeEntityRepository.addStore(storeEntity);
        if (statusResult.equals(StatusResult.OK)) {
            return Response.status(200).build();
        } else if (StatusResult.FAILED_DOUBLE.equals(statusResult)) {
            return Response.status(404).entity(
                    ErrorMessage.builder()
                            .code(404)
                            .message("Дублирование складов")
                            .build())
                    .build();
        } else if (StatusResult.FAILED_EXISTS.equals(statusResult)) {
            return Response.status(405).entity(
                    ErrorMessage.builder()
                            .code(405)
                            .message("Склад уже существует")
                            .build())
                    .build();
        } else {
            return Response.status(500).entity(
                    ErrorMessage.builder()
                            .code(500)
                            .message("Неизвестная ошибка")
                            .build())
                    .build();
        }
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<StoreEntity> getStores() {
        return storeEntityRepository.getStores();
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes("application/json")
    public void deleteStoreEntity(@PathParam("id") long id) {
        storeEntityRepository.deleteStoreById(id);
    }

    @POST
    @Path("update")
    @Consumes("application/json")
    public void updateStoreEntity(StoreEntity entity) {
        storeEntityRepository.updateStore(entity);
    }
}
