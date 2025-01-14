package com.banreservas.service;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import com.banreservas.client.CountryClient;
import com.banreservas.client.dto.response.CountryResponse;
import com.banreservas.exception.ExternalServiceException;
import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class CountryService {
    private static final Logger LOG = Logger.getLogger(CountryService.class);
    private static final String DEFAULT_LANGUAGE = "eng";
    
    @Inject
    @RestClient
    CountryClient countryClient;
    
    @CacheResult(cacheName = "demonyms-cache")
    public String getDemonymByCountryCode(String countryCode) {
        try {
            LOG.debug("Fetching demonym for country code: " + countryCode);
            List<CountryResponse> response = countryClient.getCountryByCode(countryCode);
            
            LOG.debug("Raw response: " + response); // Agregar para debug
            
            if (response == null || response.isEmpty()) {
                LOG.warn("No response received for country code: " + countryCode);
                throw new ExternalServiceException("No data found for country code: " + countryCode);
            }
            
            CountryResponse countryData = response.get(0);
            LOG.debug("Demonyms map: " + countryData.getDemonyms()); // Agregar para debug
            
            if (countryData.getDemonyms() == null || !countryData.getDemonyms().containsKey(DEFAULT_LANGUAGE)) {
                LOG.warn("No demonym found for country code: " + countryCode);
                throw new ExternalServiceException("No demonym found for country code: " + countryCode);
            }
            
            String demonym = countryData.getDemonyms().get(DEFAULT_LANGUAGE).getMasculine();
            LOG.debug("Retrieved demonym: " + demonym);
            return demonym;
            
        } catch (Exception e) {
            LOG.error("Error fetching demonym for country code: " + countryCode, e);
            throw new ExternalServiceException("Failed to fetch demonym: " + e.getMessage(), e);
        }
    }

}