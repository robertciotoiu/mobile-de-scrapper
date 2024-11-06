package com.robertciotoiu;

import com.robertciotoiu.cooldown.service.CategoryCooldownHandler;
import com.robertciotoiu.carcategory.CarCategoryMetadataService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith({MockitoExtension.class, SpringExtension.class})
//@ContextConfiguration(classes = JsoupWrapper.class)
@SpringBootTest
class ScraperServiceTest {

    @Autowired
    ScraperService scraperService;

    @Test
    void scrape() {
    }

    @Test
    void getJson() throws IOException {
        // Read the json file from the resources folder
        var json = new String(Files.readAllBytes(Paths.get("src/test/resources/0listing.html")));
        var doc = Jsoup.parse(json);
        var jsonElement = scraperService.getJson(doc);
        System.out.println(jsonElement);
        Assertions.assertNotNull(jsonElement);
    }
}