package com.musinsa.url_shorten.boundaries;

import com.musinsa.url_shorten.boundaries.api.ErrorResponse;
import com.musinsa.url_shorten.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class UrlServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBusiness(BaseException baseException, WebRequest request) {
        ErrorResponse<Object> errorResponse = ErrorResponse.builder()
                .code(baseException.getCode())
                .message(baseException.getMessage())
                .build();
        log.error("{}", errorResponse);
        return handleExceptionInternal(baseException, errorResponse, new HttpHeaders(), baseException.getHttpStatus(), request);
    }
}
