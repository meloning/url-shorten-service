package com.musinsa.url_shorten.boundaries;

import com.musinsa.url_shorten.boundaries.api.ErrorResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class ValidationInterceptor {

    @Around("execution(* com.musinsa.url_shorten.boundaries.api.controller.*.*(..))")
    public Object validAop(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) object;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errors = new LinkedHashMap<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errors.put(error.getField(), error.getDefaultMessage());
                    }
                    return ResponseEntity.badRequest().body(
                            ErrorResponse.<Map<String, String>>builder()
                                .code("invalid_parameter")
                                .message("The requester's parameter is malformed.")
                                .errors(errors)
                                .build()
                    );
                }
            }
        }

        return joinPoint.proceed(joinPoint.getArgs());
    }
}
