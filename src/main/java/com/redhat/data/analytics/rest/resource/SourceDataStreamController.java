package com.redhat.data.analytics.rest.resource;

import com.redhat.data.analytics.model.SinkDataStream;
import com.redhat.data.analytics.model.SourceDataStream;
import com.redhat.data.analytics.repo.SourceDataStreamService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by zhassan on 2016-10-25.
 */
@Path("/source")
@Api(value = "/schema")
@Stateless
public class SourceDataStreamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceDataStreamController.class);

    @Inject
    SourceDataStreamService service;

    @ApiOperation(value = "Get All SourceDataStreams", produces = "application/json",
            notes = "Provides list of SourceDataStreams persisted to the database")
    @GET
    @Path("/")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    public Response getAll() {
        LOGGER.info("get all records");
        return Response.ok(service.getAll()).build();
    }



    @ApiOperation(value = "Get SourceDataStream By Id", produces = "application/json",
            notes = "Query the database for Schema entry with the id provided in the url path")
    @GET
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response getById(
            @ApiParam(value = "ID of Schema needed", required = true) @PathParam("id") String id) {
        LOGGER.info("get id: {} ", id);
        SourceDataStream item;
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

    @ApiOperation(value = "Delete SourceDataStream By Id", produces = "application/json",
            notes = "Delete SourceDataStream from database with the id provided in the url")
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response delete(
            @ApiParam(value = "ID of SourceDataStream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("delete entry: {} ", id);
        try {
            SourceDataStream item = service.get(id);
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

    @ApiOperation(value = "Update SourceDataStream", produces = "application/json",
            notes = "Update SourceDataStream entry with the id provided in the url")
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response update(@ApiParam(value = "schema json posted", required = true) SourceDataStream record) {
        LOGGER.info("delete entry: {} ", record);
        SourceDataStream item;
        try {
            item = service.get(record.getSourceId());
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

    @ApiOperation(value = "Create SourceDataStream", produces = "application/json",
            consumes = "application/json", notes = "Inserts a new SourceDataStream into the database")
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response add(@ApiParam(value = " SourceDataStream json posted", required = true) SourceDataStream record) {
        LOGGER.info("create entry: {} ", record);
        SourceDataStream item;
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
