package com.example.ytapi.mapper;

import com.example.ytapi.model.ApiKeyEntity;
import com.example.ytapi.dto.ApiKeyDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyMapper {
    public ApiKeyEntity convertDtoToEntity(ApiKeyDTO dto) {
        ApiKeyEntity apiKeyEntity = new ApiKeyEntity();
        apiKeyEntity.setApikey((dto.getApiKey()));
        return apiKeyEntity;

    }

    public ApiKeyDTO convertEntityToDTO(ApiKeyEntity entity) {
        ApiKeyDTO apiKeyDTO = new ApiKeyDTO();
        apiKeyDTO.setApiKey(entity.getApiKey());
        return apiKeyDTO;

    }

}
