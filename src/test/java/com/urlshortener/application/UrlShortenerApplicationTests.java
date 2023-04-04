package com.urlshortener.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UrlShortenerApplicationTests {

    @Test
    void generateShortUrlTest() {
        var testUrl = Helper.generateShortURL(1000);
        var testUrl2 = Helper.generateShortURL(10000);
        var testUrl3 = Helper.generateShortURL(100000);
        var testUrl4 = Helper.generateShortURL(1000000);

        assertEquals("qi", testUrl);
        assertEquals("cLs", testUrl2);
        assertEquals("Aa4", testUrl3);
        assertEquals("emjc", testUrl4);
    }

    @Test
    void generatePasswordTest() {
        var password = Helper.generatePassword(5);
        var password2 = Helper.generatePassword(10);

        assertEquals(5, password.length());
        assertEquals(10, password2.length());
    }

}
