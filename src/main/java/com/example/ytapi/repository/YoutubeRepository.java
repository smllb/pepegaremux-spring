package com.example.ytapi.repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Repository
public class YoutubeRepository {

    public PlaylistItemListResponse queryDataFromPlaylist(String API_KEY) throws GeneralSecurityException, IOException {
        YouTube youtubeService = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .build();

        YouTube.PlaylistItems.List request = youtubeService.playlistItems()
                .list("snippet,status")
                .setFields("items(status(privacyStatus),snippet(title,resourceId)),nextPageToken")
                .setPlaylistId("PLDIoUOhQQPlXr63I_vwF9GD8sAKh77dWU")
                .setMaxResults(50L)
                .setKey(API_KEY);

        return request.execute();

    }
}
