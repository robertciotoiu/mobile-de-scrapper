package com.robertciotoiu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CrawlerLiveAccess {

        private static final String URL = "https://suchen.mobile.de/fahrzeuge/sitemap-pls-carspecification-0.xml";

    @Test
    public void testAccessUrl() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        assertNotNull(doc, "The result should not be null");
    }
}
