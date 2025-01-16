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
import com.banreservas.exception.ExternalServiceException;
import com.banreservas.dto.response.ClientResponse;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import io.smallrye.faulttolerance.api.RateLimit;


@Path("/api/v1/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;

    @Inject
    MeterRegistry registry;
    
    private Timer createClientTimer;
    private Timer getAllClientsTimer;


    @PostConstruct
    void init() {
        createClientTimer = Timer.builder("app.client.create")
            .description("Time taken to create new client")
            .register(registry);
            
        getAllClientsTimer = Timer.builder("app.client.get.all")
            .description("Time taken to retrieve all clients")
            .register(registry);
    }

    @GET
    @RateLimit(value = 100, window = 60000)
    @Operation(summary = "get all clients", description = "the endpoint to get all clients", tags = "allClients")
    public Response getAllClients() {
        return getAllClientsTimer.record(() -> 
            Response.ok(clientService.getAllClients()).build()
        );
    }

    @POST
    @RateLimit(value = 30, window = 60000)
    @Operation(summary = "Create client", description = "Creates a new client", tags = "createClient")
    @Timed(name = "Create Client", description = "Time taken to create a new client")
    public Response createClient(@Valid ClientRequest clientRequest) {
        ClientResponse response = clientService.createClient(clientRequest);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{clientId}")
    @RateLimit(value = 40, window = 60000)
    @Operation(summary = "Update client", description = "Updates an existing client by ID", tags = "updateClient")
    @Timed(name = "Update Client", description = "Time taken to Update a client existing")
    public Response updateClient(@PathParam("clientId") UUID clientId, @Valid ClientRequest clientRequest) {
        return Response.ok(clientService.updateClient(clientId, clientRequest)).build();
    }

    // @GET
    // @Path("/{clientId}")
    // @RateLimit(value = 40, window = 60000)
    // @Operation(summary = "Get client by ID", description = "Retrieves a client by ID for identify", tags = "clientById")
    // public Response getClientById(@PathParam("clientId") UUID clientId) {

    //     return Response.ok(clientService.getClientById(clientId)).build();
    // }
    
    @GET
    @Path("/{clientId}")
    @RateLimit(value = 40, window = 60000)
    @Operation(summary = "Get client by ID", description = "Retrieves a client by ID", tags = "clientById")
    @Timed(name = "Get client for Id", description = "Time taken to search a client by ID")
    public Response getClientById(@PathParam("clientId") UUID clientId) {
        return getAllClientsTimer.record(() -> 
            Response.ok(clientService.getClientById(clientId)).build()
        );
    }
   
    @GET
    @Path("/country/{countryCode}")
    @RateLimit(value = 30, window = 60000)
    @Operation(summary = "Get clients by country", description = "Retrieves all clients from a specific country" , tags = "clientsByCountry")
    @Timed(name = "Get clients by country", description = "Time taken to get list of clients by country")
    public Response getClientsByCountry(@PathParam("countryCode") String countryCode) {
        return Response.ok(clientService.getClientsByCountry(countryCode)).build();
    }

    @DELETE
    @Path("/{clientId}")
    @RateLimit(value = 30, window = 60000)
    @Operation(summary = "Delete client", description = "Deletes a client by ID" , tags = "deleteClient")
    @Timed(name = "Delete Client", description = "Time taken to delete a client existing by ID")
    public Response deleteClient(@PathParam("clientId") UUID clientId) {
        clientService.deleteClient(clientId);
        return Response.noContent().build();
    }

    @GET
    @Path("/paginate")
    @Operation(summary = "Get all clients with pagination")
    public Response getAllClientsPaginate(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        return Response.ok(clientService.getAllClientsPaginate(page, size)).build();
    }
}