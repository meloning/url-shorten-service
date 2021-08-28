package com.musinsa.url_shorten.core.model;

import com.musinsa.url_shorten.infra.domain.ShortUrlEntity;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrl {
    // primary key
    private Long id;

    // unique constraint { code, originalUrl }
    private String code;
    private String originalUrl;

    private Long requestCount;

    // ISO-8601
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void incrementRequest() {
        this.requestCount++;
    }

    public ShortUrlEntity toEntity() {
        ShortUrlEntity entity = new ShortUrlEntity();
        entity.setId(this.id);
        entity.setCode(this.code);
        entity.setOriginalUrl(this.originalUrl);
        entity.setRequestCount((this.requestCount == null) ? 0L : this.requestCount);
        entity.setCreatedAt((this.createdAt == null) ? null : this.createdAt.toInstant());
        entity.setUpdatedAt((this.updatedAt == null) ? null : this.updatedAt.toInstant());
        return entity;
    }
}
