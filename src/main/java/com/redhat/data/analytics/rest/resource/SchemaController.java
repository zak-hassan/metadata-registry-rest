package com.redhat.data.analytics.rest.resource;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.data.analytics.model.Schema;
import com.redhat.data.analytics.repo.SchemaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * SchemaController
 * 
 * @author Zak Hassan<zak.hassan@redhat.com>
 */
@Path("/schema")
@Api(value = "/schema")
@Stateless
public class SchemaController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchemaController.class);

  @Inject
  SchemaService service;


  @ApiOperation(value = "Get All Schemas", produces = "application/json",
      notes = "Provides list of schema persisted to the database")
  @GET
  @Path("/")
  @Produces("application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
  public Response getAll() {
    LOGGER.info("get all records");
    return Response.ok(service.getAll()).build();
  }

  @ApiOperation(value = "Get Schema By Id", produces = "application/json",
      notes = "Query the database for Schema entry with the id provided in the url path")
  @GET
  @Path("/{id}")
  @Produces("application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
  public Response getById(
      @ApiParam(value = "ID of Schema needed", required = true) @PathParam("id") String id) {
    LOGGER.info("get id: {} ", id);
    Schema schema;
    try {
      schema = service.get(id);
      if (schema == null) {

        throw new Exception("Schema not found");
      }
    } catch (Exception ex) {
      LOGGER.info("Invalid Request: {} ", ex.getMessage());
      return Response.status(Status.BAD_REQUEST).build();
    }
    return Response.ok(schema).build();
  }

  @ApiOperation(value = "Delete Schema By Id", produces = "application/json",
      notes = "Delete Schema from database with the id provided in the url")
  @DELETE
  @Path("/{id}")
  @Produces("application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
  public Response delete(
      @ApiParam(value = "ID of Schema needed", required = true) @PathParam("id") String id) {
    LOGGER.info("delete entry: {} ", id);
    try {
      Schema schema = service.get(id);
      if (schema == null) {
        throw new Exception("Schema not found");
      }
      service.delete(schema);
    } catch (Exception ex) {
      LOGGER.info("Invalid Request: {} ", ex.getMessage());
      return Response.status(Status.BAD_REQUEST).build();
    }
    return Response.ok().build();
  }

  @ApiOperation(value = "Update Schema", produces = "application/json",
      notes = "Update schema entry with the id provided in the url")
  @PUT
  @Path("/update")
  @Consumes("application/json")
  @Produces("application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
  public Response update(@ApiParam(value = "schema json posted", required = true) Schema schema) {
    LOGGER.info("delete entry: {} ", schema);
    Schema s;
    try {
      s = service.get(schema.getSchemaId());
      if (s == null) {
        throw new Exception("Schema not found");
      }
      service.update(schema);
    } catch (Exception ex) {
      LOGGER.info("Invalid Request: {} ", ex.getMessage());
      return Response.status(Status.BAD_REQUEST).build();
    }
    return Response.ok(s).build();
  }

  @ApiOperation(value = "Create Schema", produces = "application/json",
      consumes = "application/json", notes = "Inserts a new schema into the database")
  @POST
  @Path("/")
  @Consumes("application/json")
  @Produces("application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request Invalid Id")})
  public Response add(@ApiParam(value = "schema json posted", required = true) Schema schema) {
    LOGGER.info("create entry: {} ", schema);
    Schema addItem;
    try {
      // TODO: Need to add @Valid hibernate validator to this schema that gets sent here.
      addItem = service.add(schema);
    } catch (Exception ex) {
      LOGGER.info("Invalid Request: {} ", ex.getMessage());
      return Response.status(Status.BAD_REQUEST).build();
    }
    return Response.ok(addItem).build();
  }
}
