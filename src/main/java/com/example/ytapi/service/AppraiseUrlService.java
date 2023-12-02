package com.example.ytapi.service;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ytapi.dto.UrlResultlDTO;
import com.example.ytapi.enums.UrlType;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AppraiseUrlService {

    ApiKeyService apiKeyService;
    YouTubeService youTubeService;

    public AppraiseUrlService(ApiKeyService apiKeyService, YouTubeService youTubeService) {
        this.apiKeyService = apiKeyService;
        this.youTubeService = youTubeService;
    }

    public String getUrlId(String url, String urlType) {

        String urlId = null;

        Pattern videoPattern = Pattern.compile("(?:v=|\\/v\\/|\\/embed\\/|\\.be\\/|v=.*&v=)([^&\\n?]+)");
        Pattern playlistPattern = Pattern.compile("((?<=list=)[^&]+)");
        Matcher matcher = null;

        switch (urlType) {
            case "VIDEO":
                matcher = videoPattern.matcher(url);
                break;

            case "PLAYLIST":
                matcher = playlistPattern.matcher(url);
                break;
        }
        if (matcher != null && matcher.find()) {
            urlId = matcher.group(1);
        }

        return urlId;

    }
    public UrlResultlDTO appraiseUrl(String url) throws IOException, GeneralSecurityException {

        UrlResultlDTO urlResultlDTO = new UrlResultlDTO();

        Map<String, String> urlResult = new HashMap<>();

        if (url.contains("watch?v=") && url.contains("list=") || url.contains("/playlist?list=")){
            urlResultlDTO.setType(UrlType.PLAYLIST.toString());

        } else if (url.contains("watch?v=") || url.contains("/v/")) {
            urlResultlDTO.setType(UrlType.VIDEO.toString());

        } else {
            return null;
        }

        urlResultlDTO.setId(getUrlId(url, urlResultlDTO.getType()));
        return urlResultlDTO;


    };

}
