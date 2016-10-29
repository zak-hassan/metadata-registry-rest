package com.redhat.data.analytics.rest.resource;

import com.redhat.data.analytics.model.DataStream;
import com.redhat.data.analytics.model.Schema;
import com.redhat.data.analytics.repo.DataStreamService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by zhassan on 2016-10-26.
 */
@Path("/datastream")
@Api(value = "/datastream")
@Stateless
public class DataStreamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataStreamController.class);

    @Inject
    DataStreamService service;


    @ApiOperation(value = "Get All DataStreams", produces = "application/json",
            notes = "Provides list of datastreams persisted to the database")
    @GET
    @Path("/")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    public Response getAll() {
        LOGGER.info("get all records");
        return Response.ok(service.getAll()).build();
    }



    @ApiOperation(value = "Get DataStream By Id", produces = "application/json",
            notes = "Query the database for Schema entry with the id provided in the url path")
    @GET
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response getById(
            @ApiParam(value = "ID of Schema needed", required = true) @PathParam("id") String id) {
        LOGGER.info("get id: {} ", id);
        DataStream item;
        try {
            item = service.get(id);
            if (item == null) {

                throw new Exception("DataStream id not found");
            }
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }

    @ApiOperation(value = "Delete DataStream By Id", produces = "application/json",
            notes = "Delete DataStream from database with the id provided in the url")
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response delete(
            @ApiParam(value = "ID of datastream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("delete entry: {} ", id);
        try {
            DataStream item = service.get(id);
            if (item == null) {
                throw new Exception("Schema not found");
            }
            service.delete(item);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @ApiOperation(value = "Update DataStream", produces = "application/json",
            notes = "Update DataStream entry with the id provided in the url")
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response update(@ApiParam(value = "schema json posted", required = true) DataStream record) {
        LOGGER.info("delete entry: {} ", record);
        DataStream item;
        try {
            item = service.get(record.getDataStreamId());
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

    @ApiOperation(value = "Start DataStream", produces = "application/json",
            notes = "Start DataStream with the id provided in the url")
    @GET
    @Path("/start/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response startStream(
            @ApiParam(value = "ID of datastream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("start datastream: {} ", id);
        DataStream item;
        try {
            item = service.get(id);
            startDataStreamProcess();
            item.setStatus("started");
            if (item == null) {
                throw new Exception("datastream not found");
            }
            service.update(item);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }

    @ApiOperation(value = "Start DataStream", produces = "application/json",
            notes = "Start DataStream with the id provided in the url")
    @GET
    @Path("/stop/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response stopStream(
            @ApiParam(value = "ID of datastream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("start datastream: {} ", id);
        DataStream item;
        try {
            item = service.get(id);
            stopDataStreamProcess();
            item.setStatus("started");
            if (item == null) {
                throw new Exception("datastream not found");
            }
            service.update(item);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }


    @ApiOperation(value = "Start DataStream", produces = "application/json",
            notes = "Start DataStream with the id provided in the url")
    @GET
    @Path("/pause/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response pauseStream(
            @ApiParam(value = "ID of datastream needed", required = true) @PathParam("id") String id) {
        LOGGER.info("start datastream: {} ", id);
        DataStream item;
        try {
            item = service.get(id);
            pauseDataStreamProcess();
            item.setStatus("started");
            if (item == null) {
                throw new Exception("Datastream not found");
            }
            service.update(item);
        } catch (Exception ex) {
            LOGGER.info("Invalid Request: {} ", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(item).build();
    }


    private void startDataStreamProcess() {
        //TODO: Logic to start a datastream process
    }
    private void stopDataStreamProcess() {
        //TODO: Logic to stop a datastream process
    }
    private void pauseDataStreamProcess() {
        //TODO: Logic to pause a datastream process
    }

    @ApiOperation(value = "Create DataStream", produces = "application/json",
            consumes = "application/json", notes = "Inserts a new DataStream into the database")
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
    public Response add(@ApiParam(value = "DataStream json posted", required = true) DataStream record) {
        LOGGER.info("create entry: {} ", record);
        DataStream item;
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
