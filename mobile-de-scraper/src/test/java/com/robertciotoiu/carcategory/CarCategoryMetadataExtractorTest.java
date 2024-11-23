package com.robertciotoiu.carcategory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CarCategoryMetadataExtractorTest {
    @Autowired
    private CarCategoryMetadataExtractor carCategoryMetadataExtractor;

    @Test
    void testExtract() throws IOException {
        //TODO: reimplement this test
/*        String carSpecPageUrl = "http://www.google.com";
        File in = new File("src/test/resources/listings-with-multiple-ads.html");
        Document carSpecPageDoc = Jsoup.parse(in, null);

        CarCategoryMetadata result = carCategoryMetadataExtractor.extract(carSpecPageDoc, carSpecPageUrl);

        assertEquals(carSpecPageUrl, result.getUrl());
        assertEquals(5280, result.getTotalListings());
        assertEquals(50, result.getTotalPages());
        assertNotNull(result.getScrapedTime());*/

    }
}