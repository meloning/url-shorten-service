package com.musinsa.url_shorten.infra.domain;

import com.musinsa.url_shorten.core.model.ShortUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.ZoneId;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(
        name = "ShortUrl",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "CODE_ORIGINAL_URL_UNIQUE",
                        columnNames = {"code", "originalUrl"}
                )
        },
        indexes = @Index(name = "CODE_INDEX", columnList = "code")
)
@DynamicUpdate
public class ShortUrlEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "originalUrl", nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private Long requestCount;

    public ShortUrl toModel() {
        return ShortUrl.builder()
                .id(this.id)
                .code(this.code)
                .originalUrl(this.originalUrl)
                .requestCount((this.requestCount == null) ? 0L : this.requestCount)
                .createdAt((super.createdAt == null) ? null : super.createdAt.atZone(ZoneId.systemDefault()))
                .updatedAt((super.updatedAt == null) ? null : super.updatedAt.atZone(ZoneId.systemDefault()))
                .build();
    }
}
