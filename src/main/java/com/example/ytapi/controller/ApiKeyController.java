package com.example.ytapi.controller;

import com.example.ytapi.dto.ApiKeyDTO;
import com.example.ytapi.service.ApiKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api-key")
public class ApiKeyController {

    ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {

        this.apiKeyService = apiKeyService;

    }

    @GetMapping()
    public ResponseEntity<ApiKeyDTO> getApiKey()  throws IOException {

        ApiKeyDTO apiKeyDTO = apiKeyService.getApiKeyDTO();
        return ResponseEntity.ok(apiKeyDTO);


    }

}
