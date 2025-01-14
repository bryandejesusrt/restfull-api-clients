package com.banreservas.mapper;

// import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.banreservas.entity.Client;
import com.banreservas.dto.request.ClientRequest;
import com.banreservas.dto.response.ClientResponse;

@Mapper(componentModel = "cdi")
public interface ClientMapper {
    Client toEntity(ClientRequest request);
    ClientResponse toResponse(Client client);
    void updateClientFromRequest(ClientRequest request, @MappingTarget Client client);
}

