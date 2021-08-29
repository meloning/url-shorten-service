package com.musinsa.url_shorten.boundaries.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@ToString
public class OriginalUrlDto {
    @URL(message = "The URL format is not correct.")
    private String url;
}
