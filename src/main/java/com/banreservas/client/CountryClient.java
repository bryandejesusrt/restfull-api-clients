package com.banreservas.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import com.banreservas.client.dto.response.CountryResponse;
import java.util.List;
import com.banreservas.client.dto.response.CountryResponse;

@Path("/alpha")
@RegisterRestClient(configKey = "country-api")
public interface CountryClient {
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 2000)
    @Timeout(5000)
    List<CountryResponse> getCountryByCode(@PathParam("code") String code);
}