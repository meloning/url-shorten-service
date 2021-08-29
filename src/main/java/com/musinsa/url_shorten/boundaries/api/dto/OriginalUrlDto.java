package com.musinsa.url_shorten.boundaries.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class OriginalUrlDto {
    @NotBlank(message = "It cannot be empty or blank.")
    @URL(message = "The URL format is not correct.")
    private String url;
}
