package com.musinsa.url_shorten.infra.repository;

import com.musinsa.url_shorten.core.model.ShortUrl;
import com.musinsa.url_shorten.core.repository.ShortUrlRepository;
import com.musinsa.url_shorten.infra.domain.ShortUrlEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class ShortUrlRepositoryImpl implements ShortUrlRepository {
    private final ShortUrlEntityRepository shortUrlEntityRepository;

    @Override
    public Optional<ShortUrl> findByCode(String code) {
        return shortUrlEntityRepository.findByCode(code).map(ShortUrlEntity::toModel);
    }

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlEntityRepository.save(shortUrl.toEntity()).toModel();
    }
}
