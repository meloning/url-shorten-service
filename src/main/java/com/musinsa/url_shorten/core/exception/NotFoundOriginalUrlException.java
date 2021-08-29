package com.musinsa.url_shorten.core.exception;

import lombok.Getter;

@Getter
public class NotFoundOriginalUrlException extends BaseException {
    private final String message;

    public NotFoundOriginalUrlException(String message) {
        super("not_found_original_url", message);
        this.message = message;
    }
}
