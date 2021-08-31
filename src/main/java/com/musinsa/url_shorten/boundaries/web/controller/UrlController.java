package com.musinsa.url_shorten.boundaries.web.controller;

import com.musinsa.url_shorten.core.usecase.RestoreShortUrlUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UrlController {
    private final RestoreShortUrlUsecase restoreShortUrlUsecase;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("{shortened-url:^[a-zA-Z0-9]{3,7}$}")
    public void restore(@PathVariable("shortened-url") String shortenedUrl, HttpServletResponse response) throws IOException {
        String originalUrl = restoreShortUrlUsecase.execute(shortenedUrl);
        log.info("{}", originalUrl);
        response.sendRedirect(originalUrl);
    }
}
