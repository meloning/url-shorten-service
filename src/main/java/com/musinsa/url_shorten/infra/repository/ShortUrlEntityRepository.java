package com.musinsa.url_shorten.infra.repository;

import com.musinsa.url_shorten.infra.domain.ShortUrlEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShortUrlEntityRepository extends CrudRepository<ShortUrlEntity, Long> {
    Optional<ShortUrlEntity> findByCode(String code);
}
