package com.musinsa.url_shorten.boundaries.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ShortenUrlDto {
    private String shortenUrl;
}
