package com.robertciotoiu.carcategory;

import com.robertciotoiu.util.CarCategoryXPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.robertciotoiu.PaginationHelper.isAnySubpage;

@Component
public class CarCategoryMetadataExtractor {
    private static final Logger logger = LogManager.getLogger(CarCategoryMetadataExtractor.class);

    public CarCategoryMetadata extract(String json, String carCategoryUrl) {
        JSONTokener tokener = new JSONTokener(json);
        JSONObject jsonObject = new JSONObject(tokener);
        var searchResults = jsonObject.getJSONObject("search")
                .getJSONObject("srp")
                .getJSONObject("data")
                .getJSONObject("searchResults");

        var totalListings = searchResults.getInt("numResultsTotal");
        var totalPages = searchResults.getInt("numPages");

        var carCategoryMetadataBuilder = CarCategoryMetadata.builder();
        return carCategoryMetadataBuilder
                .url(carCategoryUrl)
                .totalListings(totalListings)
                .totalPages(totalPages)
                .scrapedTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }
}
