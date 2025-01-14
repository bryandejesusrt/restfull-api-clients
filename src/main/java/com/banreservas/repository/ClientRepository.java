package com.banreservas.repository;

import com.banreservas.entity.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {

    public List<Client> findByCountryCode(String countryCode) {
        return find("countryCode", countryCode).list();
    }
}
