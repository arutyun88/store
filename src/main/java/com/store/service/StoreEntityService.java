package com.store.service;

import com.store.model.dto.ErrorMessage;
import com.store.model.entity.StoreEntity;
import com.store.repoository.StoreEntityRepository;
import com.store.util.StatusResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.store.util.ResponseService.getErrorResponse;
import static com.store.util.ResponseService.getResponse;
import static com.store.util.InfoResult.*;

@Stateless
public class StoreEntityService {

    @Inject
    private StoreEntityRepository storeEntityRepository;

    public Response addStore(StoreEntity storeEntity) {
        StatusResult statusResult = storeEntityRepository.addStore(storeEntity);
        if (statusResult.equals(StatusResult.OK)) return Response.status(OK).build();
        else return getErrorResponse(statusResult);
    }

    public List<StoreEntity> getAllStores() {
        return storeEntityRepository.getStores();
    }

    public Response getStoreById(long id) {
        Response response = storeEntityRepository.findStoreById(id);
        return getResponse(response);
    }

    public Response getStoreByName(String storeName) {
        Response response = storeEntityRepository.findStoreByName(storeName);
        return getResponse(response);
    }

    public Response deleteStoreById(long id) {
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

    public Response updateStore(StoreEntity entity) {
        try {
            if (storeEntityRepository.checkStore(entity).size() != 0) {
                return Response.status(FAILED_DOUBLE).entity(
                        ErrorMessage.builder()
                                .code(FAILED_DOUBLE)
                                .message(FAILED_DOUBLE_MESSAGE)
                                .build())
                        .build();
            }
            storeEntityRepository.updateStore(entity);
            return Response.status(OK).build();
        } catch (NoResultException exception) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        }
    }
}
