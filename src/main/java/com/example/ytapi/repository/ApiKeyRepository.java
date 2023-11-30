package com.example.ytapi.repository;

import com.example.ytapi.dto.ApiKeyDTO;
import com.example.ytapi.model.ApiKeyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class ApiKeyRepository {

    private final File secrets = new File("C:\\Users\\yogi\\IdeaProjects\\ytapi\\src\\main\\resources\\secrets.json");

    public ApiKeyEntity getApiKey() throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(secrets, ApiKeyEntity.class);

    };

}
