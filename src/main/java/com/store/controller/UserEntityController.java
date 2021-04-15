package com.store.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("user")
public class UserEntityController {

    @GET
    @Path("test")
    @Produces("application/json")
    public String test() {
        return "TEST";
    }
}
