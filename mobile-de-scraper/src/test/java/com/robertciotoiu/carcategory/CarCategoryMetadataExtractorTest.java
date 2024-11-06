package com.robertciotoiu.carcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarCategoryMetadataExtractorTest {

    private final CarCategoryMetadataExtractor extractor;
    private String jsonContent;

    @Autowired
    public CarCategoryMetadataExtractorTest(CarCategoryMetadataExtractor extractor) {
        this.extractor = extractor;
    }

    @BeforeEach
    void setUp() throws IOException {
        jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/initialStateSample.json")));
    }

    @Test
    void testExtract() {
        String carCategoryUrl = "https://www.mobile.de/auto/volkswagen-polo-gti-dsg.html?lang=en";
        CarCategoryMetadata metadata = extractor.extract(jsonContent, carCategoryUrl);

        assertNotNull(metadata);
        assertEquals(carCategoryUrl, metadata.getUrl());
        assertEquals(988, metadata.getTotalListings());
        assertEquals(50, metadata.getTotalPages());
    }
}