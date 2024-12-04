package com.robertciotoiu.service;

import com.robertciotoiu.connection.JsoupWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarCategoryCrawlerTest {

    @Autowired
    private CarCategoryCrawler carCategoryCrawler;

    @Test
    void crawl() {
        List<String> urls = carCategoryCrawler.crawl("https://suchen.mobile.de/sitemap.xml");
        assertNotNull(urls);
        assertFalse(urls.isEmpty(), "The number of retrieved URLs should be greater than 0");
    }
}