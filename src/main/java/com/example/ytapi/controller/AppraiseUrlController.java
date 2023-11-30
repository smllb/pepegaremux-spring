package com.example.ytapi.controller;

import com.example.ytapi.service.ApiKeyService;
import com.example.ytapi.service.AppraiseUrlService;
import com.example.ytapi.service.YouTubeService;
import org.apache.http.protocol.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/appraise")
public class AppraiseUrlController {

    ApiKeyService apiKeyService;
    YouTubeService youTubeService;

    AppraiseUrlService appraiseUrlService;

    public AppraiseUrlController (ApiKeyService apiKeyService, YouTubeService youTubeService, AppraiseUrlService appraiseUrlService) {
        this.apiKeyService = apiKeyService;
        this.youTubeService = youTubeService;
        this.appraiseUrlService = appraiseUrlService;

    }

    @GetMapping("")
    public ResponseEntity<String> appraiseUrl(@RequestParam String url) throws IOException, InterruptedException, GeneralSecurityException {


        String appraisalResult;
        appraisalResult = appraiseUrlService.appraiseUrl(url);

        return ResponseEntity.ok(appraisalResult);

    }

}
