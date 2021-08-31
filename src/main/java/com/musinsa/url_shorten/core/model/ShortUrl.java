package com.musinsa.url_shorten.core.model;

import com.musinsa.url_shorten.infra.domain.ShortUrlEntity;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortUrl {
    // primary key
    private Long id;

    // unique constraint { originalUrl }
    private String originalUrl;

    private Long requestCount;

    // ISO-8601
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void incrementRequest() {
        this.requestCount++;
    }

    public ShortUrlEntity toEntity() {
        return ShortUrlEntity.builder()
                .id(this.id)
                .originalUrl(this.originalUrl)
                .requestCount((this.requestCount == null) ? 0L : this.requestCount)
                .createdAt((this.createdAt == null) ? null : this.createdAt.toInstant())
                .updatedAt((this.updatedAt == null) ? null : this.updatedAt.toInstant())
                .build();
    }
}
