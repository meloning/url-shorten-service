package com.musinsa.url_shorten.infra.repository;

import com.musinsa.url_shorten.core.model.ShortUrl;
import com.musinsa.url_shorten.infra.config.JpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaConfig.class)
class ShortUrlRepositoryImplTest {

    @Autowired
    private ShortUrlEntityRepository shortUrlEntityRepository;

    private ShortUrlRepositoryImpl shortUrlRepositoryImpl;

    @BeforeEach
    public void setup() {
        this.shortUrlRepositoryImpl = new ShortUrlRepositoryImpl(shortUrlEntityRepository);
    }

    @Test
    @DisplayName("ModelToEntity간 컨버팅이 잘되는지 테스트")
    public void save() {
        // given
        ShortUrl newShortUrl = ShortUrl.builder()
                                .code("q1w2e3r4")
                                .originalUrl("http://www.naver.com")
                                .build();
        // when
        ShortUrl createdShortUrl = shortUrlRepositoryImpl.save(newShortUrl);

        // then
        assertThat(createdShortUrl).isNotNull();
        assertThat(createdShortUrl.getCode()).isEqualTo(newShortUrl.getCode());
        assertThat(createdShortUrl.getOriginalUrl()).isEqualTo(newShortUrl.getOriginalUrl());
    }
}
