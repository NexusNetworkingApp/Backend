package com.nexus.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ZipCodeService {

    @Value("${ZIPCODE_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public ZipCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getZipCodesWithinRadius(String baseZipCode, int distance) {
        String url = String.format("https://www.zipcodeapi.com/rest/%s/multi-radius.json/%d/mile", apiKey, distance);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("zip_codes", baseZipCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        System.out.println("Sending request to URL: " + url);
        System.out.println("Request body: " + requestBody);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            List<Map<String, Object>> responses = (List<Map<String, Object>>) responseEntity.getBody().get("responses");
            if (!responses.isEmpty()) {
                Map<String, Object> response = responses.get(0);
                return (List<String>) response.get("zip_codes");
            }
        }

        throw new RuntimeException("Invalid response from ZIP code API");
    }
}
