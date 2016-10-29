package com.redhat.data.analytics.rest.resource;

import com.redhat.data.analytics.model.DataStream;
import com.redhat.data.analytics.model.SinkDataStream;
import com.redhat.data.analytics.repo.SinkDataStreamService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Zak Hassan on 2016-10-25.
 */
@Path("/sink")
@Api(value = "/sink")
@Stateless
public class SinkDataStreamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkDataStreamController.class);

    @Inject
    SinkDataStreamService service;


    @ApiOperation(value = "Get All SinkDataStreams", produces = "application/json",
            notes = "Provides list of SinkDataStreams persisted to the database")
    @GET
    @Path("/")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    public Response getAll() {
        LOGGER.info("get all records");
        return Response.ok(service.getAll()).build();
    }



    @ApiOperation(value = "Get SinkDataStream By Id", produces = "application/json",
            notes = "Query the database for Schema entry with the id provided in the url path")
    @GET
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response getById(
            @ApiParam(value = "ID of Schema needed", required = true) @PathParam("id") String id) {
        LOGGER.info("get id: {} ", id);
        SinkDataStream item;
        try {
            item = service.get(id);
            if (item == null) {

                throw new Exception("SinkDataStream id not found");
            }
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }

    @ApiOperation(value = "Delete SinkDataStream By Id", produces = "application/json",
            notes = "Delete SinkDataStream from database with the id provided in the url")
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response delete(
            @ApiParam(value = "ID of SinkDataStream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("delete entry: {} ", id);
        try {
            SinkDataStream item = service.get(id);
            if (item == null) {
                throw new Exception("SinkStream id not found");
            }
            service.delete(item);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @ApiOperation(value = "Update SinkDataStream", produces = "application/json",
            notes = "Update SinkDataStream entry with the id provided in the url")
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response update(@ApiParam(value = "schema json posted", required = true) SinkDataStream record) {
        LOGGER.info("delete entry: {} ", record);
        SinkDataStream item;
        try {
            item = service.get(record.getSinkId());
            if (item == null) {
                throw new Exception("Schema not found");
            }
            service.update(record);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }

    @ApiOperation(value = "Create SinkDataStream", produces = "application/json",
            consumes = "application/json", notes = "Inserts a new SinkDataStream into the database")
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response add(@ApiParam(value = " SinkDataStream json posted", required = true) SinkDataStream record) {
        LOGGER.info("create entry: {} ", record);
        SinkDataStream item;
        try {
            // TODO: Need to add @Valid hibernate validator to this schema that gets sent here.
            item = service.add(record);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }

}
