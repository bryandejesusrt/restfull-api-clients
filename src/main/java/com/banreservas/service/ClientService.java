package com.banreservas.service;

import java.util.Map;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import com.banreservas.entity.Client;
import com.banreservas.mapper.ClientMapper;
import com.banreservas.client.CountryClient;
import com.banreservas.service.CountryService;
import java.util.UUID;
import com.banreservas.dto.request.ClientRequest;
import com.banreservas.dto.response.ClientResponse;
import com.banreservas.repository.ClientRepository;
import com.banreservas.exception.ExternalServiceException;
import java.util.stream.Collectors;
import java.util.List;
import org.jboss.logging.Logger;
import com.banreservas.dto.response.PageResponse;

// import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
// import org.eclipse.microprofile.faulttolerance.Fallback;

import java.util.List;

@ApplicationScoped
public class ClientService {
    private static final Logger LOG = Logger.getLogger(ClientService.class);

    @Inject
    private ClientMapper mapper;

    @Inject 
    private ClientRepository clientRepository;

    @Inject
    private CountryService countryService;

    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        if (request.countryCode == null || request.countryCode.isEmpty()) {
            throw new IllegalArgumentException("the country code cannot be empty");
        }
        Client client = mapper.toEntity(request);
        String demonym = countryService.getDemonymByCountryCode(request.countryCode);
        client.demonym = demonym;
        client.persist();
        return mapper.toResponse(client);
    }

    @Transactional
    public ClientResponse updateClient(UUID id, ClientRequest request) {
        Client client = Client.findById(id);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        client.email = request.email;
        client.address = request.address;
        client.phone = request.phone;
        client.countryCode = request.countryCode;
        client.persist();
        return mapper.toResponse(client);
    }
    
    public List<Client> getAllClients() {
        return Client.listAll();
    }

    public PageResponse<ClientResponse> getAllClientsPaginate(int page, int size) {
        List<Client> clients = Client.findAll()
            .page(page, size)
            .list();
            
        PageResponse<ClientResponse> response = new PageResponse<>();
        response.items = clients.stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
        response.totalItems = Client.count();
        response.page = page;
        response.size = size;
        response.totalPages = (int) Math.ceil((double) response.totalItems / size);
        
        return response;
    }

    public ClientResponse getClientById(UUID id) {
        Client client = Client.findById(id);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        return mapper.toResponse(client);
    }
    
    public List<Client> getClientsByCountry(String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new IllegalArgumentException("the country code cannot be empty");
        }
        return clientRepository.findByCountryCode(countryCode);
    }

    @Transactional
    public void deleteClient(UUID id) {
        Client client = Client.findById(id);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        client.delete();
    }

}
