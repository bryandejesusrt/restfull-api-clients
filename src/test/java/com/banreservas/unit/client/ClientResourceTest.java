// package com.banreservas.controller;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import com.banreservas.dto.request.ClientRequest;
// import com.banreservas.dto.response.ClientResponse;
// import com.banreservas.entity.Client;
// import com.banreservas.mapper.ClientMapper;
// import com.banreservas.service.ClientService;
// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.RestAssured;
// import jakarta.inject.Inject;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import java.util.Collections;
// import java.util.UUID;
// import jakarta.ws.rs.core.Response;

// @QuarkusTest
// public class ClientResourceTest {

//     @InjectMocks
//     ClientResource clientResource;

//     @Mock
//     ClientService clientService;

//     @Inject
//     ClientMapper clientMapper;

//     @BeforeEach
//     void setUp() {
//         // MockitoAnnotations.openMocks(this);
//     }

//     @Test
// void testCreateClientSuccess() {
//     // Given
//     ClientRequest request = new ClientRequest();
//     request.firstName = "John";
//     request.lastName = "Doe";
//     request.email = "john.doe@example.com"; 
//     request.address = "123 Main Street";
//     request.phone = "1234567890";
//     request.countryCode = "US";
//     request.demonym = "American";

//     // Creamos la respuesta esperada
//     ClientResponse response = new ClientResponse();
//     response.id = UUID.randomUUID();
    
//     when(clientService.createClient(request)).thenReturn(response);

//     // When
//     Response result = clientResource.createClient(request);

//     // Then
//     assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
//     assertNotNull(result.getEntity());
//     verify(clientService).createClient(request);
// }


//     // @Test
//     // void testUpdateClientSuccess() {
//     //     // Given
//     //     UUID clientId = UUID.randomUUID();
//     //     ClientRequest request = new ClientRequest();
//     //     request.setName("Jane Doe");
//     //     ClientResponse response = new ClientResponse();
//     //     response.setId(clientId);
//     //     when(clientService.updateClient(clientId, request)).thenReturn(response);

//     //     // When
//     //     Response result = clientResource.updateClient(clientId, request);

//     //     // Then
//     //     assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
//     //     assertNotNull(result.getEntity());
//     //     verify(clientService).updateClient(clientId, request);
//     // }

//     // @Test
//     // void testGetAllClientsReturnsEmptyList() {
//     //     // Given
//     //     when(clientService.getAllClients()).thenReturn(Collections.emptyList());

//     //     // When
//     //     Response result = clientResource.getAllClients();

//     //     // Then
//     //     assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
//     //     assertTrue(((java.util.List<?>) result.getEntity()).isEmpty());
//     //     verify(clientService).getAllClients();
//     // }

//     // @Test
//     // void testGetClientByIdSuccess() {
//     //     // Given
//     //     UUID clientId = UUID.randomUUID();
//     //     ClientResponse response = new ClientResponse();
//     //     response.setId(clientId);
//     //     when(clientService.getClientById(clientId)).thenReturn(response);

//     //     // When
//     //     Response result = clientResource.getClientById(clientId);

//     //     // Then
//     //     assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
//     //     assertNotNull(result.getEntity());
//     //     verify(clientService).getClientById(clientId);
//     // }

//     // @Test
//     // void testGetClientsByCountrySuccess() {
//     //     // Given
//     //     String countryCode = "US";
//     //     when(clientService.getClientsByCountry(countryCode)).thenReturn(Collections.emptyList());

//     //     // When
//     //     Response result = clientResource.getClientsByCountry(countryCode);

//     //     // Then
//     //     assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
//     //     assertTrue(((java.util.List<?>) result.getEntity()).isEmpty());
//     //     verify(clientService).getClientsByCountry(countryCode);
//     // }

//     // @Test
//     // void testDeleteClientSuccess() {
//     //     // Given
//     //     UUID clientId = UUID.randomUUID();
//     //     doNothing().when(clientService).deleteClient(clientId);

//     //     // When
//     //     Response result = clientResource.deleteClient(clientId);

//     //     // Then
//     //     assertEquals(Response.Status.NO_CONTENT.getStatusCode(), result.getStatus());
//     //     verify(clientService).deleteClient(clientId);
//     // }
// }
