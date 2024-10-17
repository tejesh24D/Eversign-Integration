package com.example.eversign_integration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EversignService {

    @Value("${eversign.api.key}")
    private String apiKey;

    @Value("${eversign.business.id}")
    private String businessId;

    private final RestTemplate restTemplate;

    public EversignService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDocument(String documentId, String type) {
        // Validate input
        if (documentId == null || documentId.isEmpty()) {
            throw new IllegalArgumentException("Document ID must not be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type must not be null or empty");
        }

        // Build the request URL
        String url = UriComponentsBuilder.fromHttpUrl("https://api.eversign.com/api")
                .path("/documents/" + documentId)
                .queryParam("access_key", apiKey)
                .queryParam("business_id", businessId)
                .queryParam("type", type)
                .toUriString();

        // Log URL for debugging
        System.out.println("Eversign URL: " + url);

        // Make the API call
        return restTemplate.getForObject(url, String.class);
    }
}
