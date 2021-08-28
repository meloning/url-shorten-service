package com.musinsa.url_shorten.core.usecase;

import com.musinsa.url_shorten.core.model.ShortUrl;
import com.musinsa.url_shorten.core.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CreateShortUrlUsecaseTest {

    @MockBean
    private ShortUrlRepository shortUrlRepositoryMock;

    private CreateShortUrlUsecase createShortUrlUsecase;

    @BeforeEach
    public void setup() {
        this.createShortUrlUsecase = new CreateShortUrlUsecase(shortUrlRepositoryMock);
    }

    @Test
    @DisplayName("동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.")
    public void testReturnSameShortenKeyForRequestSameUrl() {
        // given
        String testOriginalUrl = "https://blog.meloning.com";
        Optional<ShortUrl> optionalShortUrl = Optional.of(ShortUrl.builder()
                .id(100000000L)
                .originalUrl(testOriginalUrl)
                .requestCount(0L)
                .build());

        given(shortUrlRepositoryMock.findByOriginalUrl(anyString()))
                .willReturn(optionalShortUrl);

        // when
        String shortUrl = createShortUrlUsecase.execute(testOriginalUrl);

        // then
        assertThat(shortUrl).isEqualTo("/O9alG");
    }

    @Test
    @DisplayName("새로운 URL에 대해서 ShortUrl을 생성해야한다.")
    public void testReturnNewShortenKeyForRequestNewLongUrl() {
        // given
        String testOriginalUrl = "https://blog.meloning.com/where/place/sad";

        ShortUrl savedShortUrl = ShortUrl.builder()
                .id(100000000L)
                .originalUrl(testOriginalUrl)
                .requestCount(0L)
                .build();

        given(shortUrlRepositoryMock.findByOriginalUrl(anyString())).willReturn(Optional.empty());
        given(shortUrlRepositoryMock.save(any())).willReturn(savedShortUrl);

        // when
        String shortUrl = createShortUrlUsecase.execute(testOriginalUrl);

        // then
        assertThat(shortUrl).isEqualTo("/O9alG");
    }
}
