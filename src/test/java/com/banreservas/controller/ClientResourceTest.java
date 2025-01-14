// package com.banreservas.controller;

// import io.quarkus.test.junit.QuarkusTest;
// import io.quarkus.test.junit.mockito.InjectMock;
// import jakarta.inject.Inject;
// import org.junit.jupiter.api.Test;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import com.banreservas.service.ClientService;
// import com.banreservas.dto.request.ClientRequest;
// import com.banreservas.dto.response.ClientResponse;
// import jakarta.ws.rs.core.Response;
// import java.util.UUID;

// @QuarkusTest

// public class ClientResourceTest {
//     @InjectMock
//     ClientService clientService;

//     @Inject
//     ClientResource clientResource;

//     @Test
//     void testCreateClientSuccess() {
//         // Given
//         ClientRequest request = new ClientRequest();
//         request.firstName = "John";
//         request.lastName = "Doe";
//         request.email = "john.doe@example.com"; 
//         request.address = "123 Main Street";
//         request.phone = "1234567890";
//         request.countryCode = "US";

//         ClientResponse expectedResponse = new ClientResponse();
//         expectedResponse.id = UUID.randomUUID();
//         expectedResponse.firstName = request.firstName;
//         expectedResponse.lastName = request.lastName;
        
//         when(clientService.createClient(any(ClientRequest.class))).thenReturn(expectedResponse);

//         // When
//         Response result = clientResource.createClient(request);

//         // Then
//         assertEquals(Response.Status.CREATED.getStatusCode(), result.getStatus());
//         assertNotNull(result.getEntity());
//         verify(clientService).createClient(request);
//     }
// }