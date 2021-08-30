package com.musinsa.url_shorten.boundaries.web.controller;

import com.musinsa.url_shorten.core.usecase.RestoreShortUrlUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class UrlController {
    private final RestoreShortUrlUsecase restoreShortUrlUsecase;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("{shortened-url:[0-9a-zA-Z]{7}}")
    public void restore(@PathVariable("shortened-url") String shortenedUrl, HttpServletResponse response) throws IOException {
        String originalUrl = restoreShortUrlUsecase.execute(shortenedUrl);
        response.sendRedirect(originalUrl);
    }
}
