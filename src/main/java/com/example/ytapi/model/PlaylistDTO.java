package com.example.ytapi.model;

import com.google.api.services.youtube.model.PlaylistItem;

import java.util.List;

public class PlaylistDTO {


    public PlaylistDTO(List<PlaylistItem> playlistItemList) {
        this.playlistItemList = playlistItemList;
    }

    List<PlaylistItem> playlistItemList;

    public List<PlaylistItem> getPlaylistItemList() {
        return playlistItemList;
    }

    public void setPlaylistItemList(List<PlaylistItem> playlistItemList) {
        this.playlistItemList = playlistItemList;
    }
}
