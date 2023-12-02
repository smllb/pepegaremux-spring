package com.example.ytapi.controller;

import com.example.ytapi.dto.ApiKeyDTO;
import com.example.ytapi.dto.UrlResultlDTO;
import com.example.ytapi.mapper.ApiKeyMapper;
import com.example.ytapi.model.PlaylistDTO;
import com.example.ytapi.service.ApiKeyService;
import com.example.ytapi.service.AppraiseUrlService;
import com.example.ytapi.service.YouTubeService;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/appraise")
public class AppraiseUrlController {

    ApiKeyService apiKeyService;
    ApiKeyMapper apiKeyMapper;
    YouTubeService youTubeService;

    AppraiseUrlService appraiseUrlService;

    public AppraiseUrlController (ApiKeyService apiKeyService, YouTubeService youTubeService, AppraiseUrlService appraiseUrlService, ApiKeyMapper apiKeyMapper) {
        this.apiKeyService = apiKeyService;
        this.youTubeService = youTubeService;
        this.appraiseUrlService = appraiseUrlService;
        this.apiKeyMapper = apiKeyMapper;

    }

    @GetMapping("/link")
    public ResponseEntity<UrlResultlDTO> appraiseUrl(@RequestParam String url) throws IOException, InterruptedException, GeneralSecurityException {

        UrlResultlDTO urlResultlDTO = appraiseUrlService.appraiseUrl(url);
        return ResponseEntity.ok(urlResultlDTO);

    }

    @GetMapping("/page")
    public ResponseEntity<PlaylistItemListResponse>  query(@RequestParam String id, String nextPageToken) throws GeneralSecurityException, IOException {

        ApiKeyDTO apiKeyDTO = apiKeyService.getApiKeyDTO();
        return ResponseEntity.ok(youTubeService.queryPlaylistPage(apiKeyDTO.getApiKey(), id, nextPageToken));

    }

    @GetMapping("/playlist")
    public ResponseEntity<List<PlaylistItem>> query(String id) throws IOException, GeneralSecurityException {

        ApiKeyDTO apiKeyDTO = apiKeyService.getApiKeyDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO(youTubeService.queryPlaylist(apiKeyDTO.getApiKey(), id));
        return ResponseEntity.ok(playlistDTO.getPlaylistItemList());

    }

}
