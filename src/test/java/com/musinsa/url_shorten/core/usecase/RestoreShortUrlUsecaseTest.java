package com.musinsa.url_shorten.core.usecase;

import com.musinsa.url_shorten.core.exception.NotFoundOriginalUrlException;
import com.musinsa.url_shorten.core.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class RestoreShortUrlUsecaseTest {
    @MockBean
    private ShortUrlRepository shortUrlRepositoryMock;

    private RestoreShortUrlUsecase restoreShortUrlUsecase;

    @BeforeEach
    public void setup() {
        this.restoreShortUrlUsecase = new RestoreShortUrlUsecase(shortUrlRepositoryMock);
    }

    @Test
    @DisplayName("Shortening된 URL이 DB에 없을 경우, Exception 발생이 발생한다.")
    public void testThrowExceptionWhenShortenedUrlIsNotInTheDB() {
        // given
        String testShortenUrl = "O9alG";
        given(shortUrlRepositoryMock.findById(anyLong())).willReturn(Optional.empty());
        // when, then
        assertThatThrownBy(() -> restoreShortUrlUsecase.execute(testShortenUrl))
                .isInstanceOf(NotFoundOriginalUrlException.class)
                .hasMessageContaining(String.format("Not found originalUrl about '%s'", testShortenUrl));
    }

}
