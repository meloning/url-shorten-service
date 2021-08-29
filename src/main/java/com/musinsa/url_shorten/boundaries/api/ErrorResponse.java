package com.musinsa.url_shorten.boundaries.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ErrorResponse<T> {
    private String code;
    private String message;
    private T errors;
}
