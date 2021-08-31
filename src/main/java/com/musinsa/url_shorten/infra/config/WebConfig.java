package com.musinsa.url_shorten.infra.config;

import com.musinsa.url_shorten.boundaries.interceptor.LogMDCInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LogMDCInterceptor logMDCInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logMDCInterceptor).addPathPatterns("/**");
    }
}
