package com.banreservas;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.RestAssured;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import com.banreservas.service.ClientService;
import com.banreservas.dto.request.ClientRequest;
import com.banreservas.dto.response.ClientResponse;
import com.banreservas.entity.Client;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class GreetingResourceTest {
    
    private static final String BASE_PATH = "/api/v1/clients/";

    @InjectMock
    ClientService clientService;

    
    private Client testClient;  

    
    private List<Client> testClients;

    @BeforeEach
    void setUp() {
        testClient = new Client();
        testClient.id = UUID.randomUUID();
        testClient.firstName = "John";
        testClient.lastName = "Doe";
        testClient.email = "john.doe@example.com";
        testClient.address = "123 Main Street";
        testClient.phone = "1234567890";
        testClient.countryCode = "DO";

        testClients = Arrays.asList(testClient);
    }

    @Test
    void getAllClients_ShouldReturnClientsList() {
        when(clientService.getAllClients()).thenReturn(testClients);
        
        given()
            .when()
            .get(BASE_PATH)
            .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getClientById_ShouldReturnClient() {
        when(clientService.getClientById(testClientResponse.id))
            .thenReturn(testClientResponse);
        
        given()
            .when()
            .get(BASE_PATH + testClientResponse.id)
            .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }
    
    @Test
    void getClientsByCountryCode_ShouldReturnClientsList() {
        when(clientService.getClientsByCountry(testClientResponse.countryCode))
            .thenReturn(testClientsResponse);
        
        given()
            .when()
            .get(BASE_PATH + "country/" + testClientResponse.countryCode)
            .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }
}