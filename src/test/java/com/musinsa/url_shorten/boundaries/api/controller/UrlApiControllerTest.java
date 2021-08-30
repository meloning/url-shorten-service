package com.musinsa.url_shorten.boundaries.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.url_shorten.boundaries.api.dto.OriginalUrlDto;
import com.musinsa.url_shorten.core.usecase.CreateShortUrlUsecase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UrlApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateShortUrlUsecase createShortUrlUsecase;

    @Test
    @DisplayName("완전한 URL 형태로 잘 나오는지 테스트")
    public void testCorrectUrlFormat() throws Exception {
        // given
        String testUrl = "https://blog.meloning.com";
        OriginalUrlDto testOriginalUrlDto = new OriginalUrlDto();
        testOriginalUrlDto.setUrl(testUrl);

        given(createShortUrlUsecase.execute(anyString())).willReturn("/B");

        // when
        ResultActions resultActions = mockMvc.perform(post("/v1/url:shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(testOriginalUrlDto))
        );

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("shortenUrl").value("http://localhost/B"))
                .andDo(print());
    }

    @Test
    @DisplayName("잘못된 URL 형태로 요청시 Exception을 잘 응답하는지 테스트")
    public void testToReturnErrorResponseIntoInvalidUrlformat() throws Exception {
        // given
        String testUrl = "https/blog.meloning.com";
        OriginalUrlDto testOriginalUrlDto = new OriginalUrlDto();
        testOriginalUrlDto.setUrl(testUrl);

        // when
        ResultActions resultActions = mockMvc.perform(post("/v1/url:shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(testOriginalUrlDto))
        );

        // then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value("invalid_parameter"))
                .andExpect(jsonPath("message").value("The requester's parameter is malformed."))
                .andExpect(jsonPath("$.errors").hasJsonPath())
                .andExpect(jsonPath("$.errors.url").value("The URL format is not correct."))
                .andDo(print());
    }

}