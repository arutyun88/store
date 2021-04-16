package com.store.controller;

import com.store.model.document.MovementEntity;
import com.store.service.MovementEntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("movement")
public class MovementEntityController {

    @Inject
    private MovementEntityService service;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<MovementEntity> getAllMovements() {
        return service.getAllMovements();
    }
}
