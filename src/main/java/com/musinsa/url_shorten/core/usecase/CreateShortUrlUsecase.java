package com.musinsa.url_shorten.core.usecase;

import com.musinsa.url_shorten.core.model.ShortUrl;
import com.musinsa.url_shorten.core.repository.ShortUrlRepository;
import com.musinsa.url_shorten.util.Base62Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreateShortUrlUsecase {
    private final ShortUrlRepository shortUrlRepository;

    @Transactional
    public String execute(String originalUrl) {
        Optional<ShortUrl> shortUrlOptional = shortUrlRepository.findByOriginalUrl(originalUrl);
        if (shortUrlOptional.isPresent()) {
            ShortUrl shortUrl = shortUrlOptional.get();
            return File.separator + Base62Utils.encoding(shortUrl.getId().intValue());
        }

        ShortUrl newShortUrl = ShortUrl.builder()
                .originalUrl(originalUrl)
                .build();

        ShortUrl savedShortUrl = shortUrlRepository.save(newShortUrl);

        return File.separator + Base62Utils.encoding(savedShortUrl.getId().intValue());
    }
}
