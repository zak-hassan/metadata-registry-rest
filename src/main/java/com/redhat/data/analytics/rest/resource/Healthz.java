package com.redhat.data.analytics.rest.resource;

import com.redhat.data.analytics.model.CustomerBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by zhassan on 2016-10-26.
 */
@Path("/healthz")
public class Healthz {


    @GET
    @Path("/")
    @Produces("application/json")
    public Response getCustomerById() {
        return Response.ok("success").build();
    }

}
