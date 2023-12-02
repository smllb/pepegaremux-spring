package com.example.ytapi.service;

import com.example.ytapi.repository.YoutubeRepository;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class YouTubeService {

    public YouTubeService(YoutubeRepository youtubeRepository) {
        this.youtubeRepository = youtubeRepository;
    }

    YoutubeRepository youtubeRepository;


    public PlaylistItemListResponse queryPlaylistPage(String API_KEY, String id, String pageToken) throws IOException, GeneralSecurityException {

        return youtubeRepository.queryDataFromPlaylistPage(API_KEY, id, pageToken);

    }

    public List<PlaylistItem> queryPlaylist(String API_KEY, String id) throws GeneralSecurityException, IOException {

        List<PlaylistItem> playlistItems = new ArrayList<>();
        PlaylistItemListResponse playlistPage;

        playlistPage = youtubeRepository.queryDataFromPlaylistPage(API_KEY, id, "");
        playlistItems = playlistPage.getItems();

        while (playlistPage.getNextPageToken() != null && !playlistPage.getNextPageToken().isEmpty()) {

            playlistPage = youtubeRepository.queryDataFromPlaylistPage(API_KEY, id, playlistPage.getNextPageToken());
            playlistItems.addAll(playlistPage.getItems());

        }

        return playlistItems.stream().filter(i -> i.getStatus().getPrivacyStatus().equals("public")).collect(Collectors.toList());

    }


}


