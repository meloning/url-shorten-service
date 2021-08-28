package com.musinsa.url_shorten.core.repository;

import com.musinsa.url_shorten.core.model.ShortUrl;

import java.util.Optional;

public interface ShortUrlRepository {
    Optional<ShortUrl> findByOriginalUrl(String originalUrl);

    ShortUrl save(ShortUrl shortUrl);
}
