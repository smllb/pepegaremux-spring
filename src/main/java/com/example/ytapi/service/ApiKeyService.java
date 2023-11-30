package com.example.ytapi.service;

import com.example.ytapi.mapper.ApiKeyMapper;
import com.example.ytapi.repository.ApiKeyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ytapi.dto.ApiKeyDTO;
import com.example.ytapi.model.ApiKeyEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiKeyService {

    private ApiKeyRepository apiKeyRepository;
    private ApiKeyMapper apiKeyMapper;


    public ApiKeyService(ApiKeyRepository apiKeyRepository, ApiKeyMapper apiKeyMapper) {
        this.apiKeyRepository = apiKeyRepository;
        this.apiKeyMapper = apiKeyMapper;
    }

    public ApiKeyDTO getApiKeyDTO() throws IOException {
        ApiKeyEntity apiKey = apiKeyRepository.getApiKey();
        return apiKeyMapper.convertEntityToDTO(apiKey);

    };

    public String fetchApiKey() {

        HttpResponse<String> response;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api-key"))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());


        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
            return "failure";
        }

        return response.body();

    };



}
