package com.banreservas.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import java.util.UUID;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.banreservas.service.ClientService;
import com.banreservas.dto.request.ClientRequest;
import com.banreservas.entity.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.micrometer.core.annotation.Timed;
import com.banreservas.exception.ExternalServiceException;
import com.banreservas.dto.response.ClientResponse;

// import org.eclipse.microprofile.metrics.annotation.Timed;
// import org.eclipse.microprofile.openapi.annotations.Operation;
// import jakarta.enterprise.context.ApplicationScoped;
// import org.eclipse.microprofile.metrics.annotation.Gauge;
// import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Path("/api/v1/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;
    
    @POST
    // @Timed(name = "createClientTimer")
    @Operation(summary = "Create new client", description = "Creates a new client in the database for the given request", tags = "clients")
    public Response createClient(@Valid ClientRequest clientRequest) {
        ClientResponse response = clientService.createClient(clientRequest);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{clientId}")
    @Operation(summary = "Update client", description = "Updates an existing client by ID")
    public Response updateClient(@PathParam("clientId") UUID clientId, @Valid ClientRequest clientRequest) {
        return Response.ok(clientService.updateClient(clientId, clientRequest)).build();
    }

    @GET
    @Operation(summary = "Get all clients", description = "Retrieves all clients in the database")
    public Response getAllClients() {
        return Response.ok(clientService.getAllClients()).build();
    }
     
    @GET
    @Path("/{clientId}")
    @Operation(summary = "Get client by ID", description = "Retrieves a client by its unique ID")
    public Response getClientById(@PathParam("clientId") UUID clientId) {
        return Response.ok(clientService.getClientById(clientId)).build();
    }
   
    @GET
    @Path("/country/{countryCode}")
    @Operation(summary = "Get clients by country", description = "Retrieves all clients from a specific country")
    public Response getClientsByCountry(@PathParam("countryCode") String countryCode) {
        return Response.ok(clientService.getClientsByCountry(countryCode)).build();
    }

    @DELETE
    @Path("/{clientId}")
    @Operation(summary = "Delete client", description = "Deletes a client by ID")
    public Response deleteClient(@PathParam("clientId") UUID clientId) {
        clientService.deleteClient(clientId);
        return Response.noContent().build();
    }
}