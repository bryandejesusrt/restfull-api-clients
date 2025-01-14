package com.banreservas.client.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryResponse {
    @JsonProperty("demonyms")
    private Map<String, DemonymInfo> demonyms;
    
    public Map<String, DemonymInfo> getDemonyms() {
        return demonyms;
        }
    
    public void setDemonyms(Map<String, DemonymInfo> demonyms) {
        this.demonyms = demonyms;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DemonymInfo {
        @JsonProperty("f")
        private String female;
        
        @JsonProperty("m")
        private String masculine;
        
        public String getFemale() { return female; }
        public void setFemale(String female) { this.female = female; }
        public String getMasculine() { return masculine; }
        public void setMasculine(String masculine) { this.masculine = masculine; }
    }
}