package com.musinsa.url_shorten.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundOriginalUrlException extends BaseException {
    private final String message;

    public NotFoundOriginalUrlException(HttpStatus httpStatus, String message) {
        super(httpStatus, "not_found_original_url", message);
        this.message = message;
    }
}
