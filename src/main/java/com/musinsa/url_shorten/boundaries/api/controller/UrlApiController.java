package com.musinsa.url_shorten.boundaries.api.controller;


import com.musinsa.url_shorten.boundaries.api.dto.OriginalUrlDto;
import com.musinsa.url_shorten.core.usecase.CreateShortUrlUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UrlApiController {
    private final CreateShortUrlUsecase createShortUrlUsecase;

    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(@Valid @RequestBody OriginalUrlDto originalUrlDto, BindingResult bindingResult) {
        return ResponseEntity.ok(createShortUrlUsecase.execute(originalUrlDto.getUrl()));
    }
}