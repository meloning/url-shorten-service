package com.musinsa.url_shorten.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class Base62UtilsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, Integer.MAX_VALUE})
    @DisplayName("Base62 방식의 인코딩 테스트")
    public void base62EncodingTest(int id) {
        String result = Base62Utils.encoding(id);
//        System.out.println(result);
        assertThat(result).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"SbC", "uA0", "CJME", "U2xf", "O9alG"})
    @DisplayName("Base62 방식의 디코딩 테스트")
    public void base62DecodingTest(String encodedValue) {
        int id = Base62Utils.decoding(encodedValue);
//        System.out.println(id);
        assertThat(id).isPositive();
    }
}
