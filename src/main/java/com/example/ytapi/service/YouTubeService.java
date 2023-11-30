package com.example.ytapi.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class YouTubeService {

    YouTubeService youTubeService;

    public YouTubeService(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    public PlaylistItemListResponse retrievePlaylistQuery(String url) {

        PlaylistItemListResponse queryResult = youTubeService.retrievePlaylistQuery(url);
        return queryResult;
    }

}
