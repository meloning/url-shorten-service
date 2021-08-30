package com.musinsa.url_shorten.core.usecase;

import com.musinsa.url_shorten.core.exception.NotFoundOriginalUrlException;
import com.musinsa.url_shorten.core.model.ShortUrl;
import com.musinsa.url_shorten.core.repository.ShortUrlRepository;
import com.musinsa.url_shorten.util.Base62Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class RestoreShortUrlUsecase {
    private final ShortUrlRepository shortUrlRepository;

    @Transactional
    public String execute(String shortenedUrl) {
        Long id = Integer.valueOf(Base62Utils.decoding(shortenedUrl)).longValue();
        ShortUrl shortUrl = shortUrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundOriginalUrlException(HttpStatus.BAD_REQUEST, String.format("Not found originalUrl about '%s'", shortenedUrl)));
        shortUrl.incrementRequest();
        shortUrlRepository.save(shortUrl);

        return shortUrl.getOriginalUrl();
    }
}
