package com.musinsa.url_shorten.boundaries.api.controller;


import com.musinsa.url_shorten.boundaries.api.dto.OriginalUrlDto;
import com.musinsa.url_shorten.boundaries.api.dto.ShortenUrlDto;
import com.musinsa.url_shorten.core.usecase.CreateShortUrlUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UrlApiController {
    private final CreateShortUrlUsecase createShortUrlUsecase;

    @PostMapping("/url:shorten")
    public ResponseEntity<?> shorten(@Valid @RequestBody OriginalUrlDto originalUrlDto, BindingResult bindingResult) {
        String uriPath = createShortUrlUsecase.execute(originalUrlDto.getUrl());
        String fullUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(uriPath)
                .toUriString();
        log.info("{}", fullUri);
        return new ResponseEntity<>(ShortenUrlDto.builder().shortenUrl(fullUri).build(), HttpStatus.CREATED);
    }
}
