package com.robertciotoiu;

import com.robertciotoiu.connection.JsoupWrapper;
import com.robertciotoiu.cooldown.service.CategoryCooldownHandler;
import com.robertciotoiu.carcategory.CarCategoryMetadataService;
import com.robertciotoiu.listing.ListingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScraperService {
    private static final Logger logger = LogManager.getLogger(ScraperService.class);
    final JsoupWrapper jsoupWrapper;
    final ListingService listingService;
    final CarCategoryMetadataService carCategoryMetadataService;
    final CategoryCooldownHandler categoryCooldownHandler;
    final CarCategoryParsableUrlExtractor carCategoryParsableUrlExtractor;

    @Autowired
    public ScraperService(JsoupWrapper jsoupWrapper, ListingService listingService, CarCategoryMetadataService carCategoryMetadataService, CategoryCooldownHandler categoryCooldownHandler, CarCategoryParsableUrlExtractor carCategoryParsableUrlExtractor) {
        this.jsoupWrapper = jsoupWrapper;
        this.listingService = listingService;
        this.carCategoryMetadataService = carCategoryMetadataService;
        this.categoryCooldownHandler = categoryCooldownHandler;
        this.carCategoryParsableUrlExtractor = carCategoryParsableUrlExtractor;
    }


    public void scrape(String carSpecPageUrl) {
        try {
            if (isScrapable(carSpecPageUrl)) {
                var carSpecPage = jsoupWrapper.getHtml(carSpecPageUrl);
                var json = getJson(carSpecPage);
                if (json == null) {
                    return;
                }
                scrape(json, carSpecPageUrl);
                setCooldown(carSpecPage, carSpecPageUrl);
            }
        } catch (IOException e) {
            logger.warn("Error accessing CarSpec URL to extract listings. URL: {}. Exception: {}", carSpecPageUrl, e);
        }
    }

    private void scrape(String json, String carSpecPageUrl) {
        listingService.scrapeAndIngestListings(json, carSpecPageUrl);
        carCategoryMetadataService.scrapeAndIngestCarCategoryMetadata(json, carSpecPageUrl);
    }

    private void setCooldown(Document carSpecPage, String carSpecPageUrl) {
        var carCategoryUrls = carCategoryParsableUrlExtractor.extractParsableUrls(carSpecPageUrl, carSpecPage);
        categoryCooldownHandler.calculateAndSetCooldown(carCategoryUrls);
    }

    //TODO: debug this https://www.jetbrains.com/help/idea/run-and-debug-a-spring-boot-application-using-docker-compose.html#spring_boot_run_config
    private boolean isScrapable(String carSpecPageUrl) {
        return !carCategoryMetadataService.isFirstPage(carSpecPageUrl) || !categoryCooldownHandler.hasCooldown(carSpecPageUrl);
    }

    public String getJson(Document listingPage) {
        String listingsJson = null;

        // Select the script element containing window.__INITIAL_STATE__
        Element scriptElement = listingPage.select("script").stream()
                .filter(element -> element.html().contains("window.__INITIAL_STATE__"))
                .findFirst()
                .orElse(null);

        if (scriptElement != null) {
            // Extract the script content
            String scriptContent = scriptElement.html();

            // Extract the JSON part from the script content
            int startIndex = scriptContent.indexOf("window.__INITIAL_STATE__ = ") + "window.__INITIAL_STATE__ = ".length();
            int endIndex = scriptContent.indexOf("window.__PUBLIC_CONFIG__", startIndex) + 1;
            listingsJson = scriptContent.substring(startIndex, endIndex);

            // Print the extracted JSON
            logger.debug(listingsJson);
        } else {
            logger.error("Script element containing window.__INITIAL_STATE__ not found for URL: {}", listingPage.location());
        }

        return listingsJson;
    }
}
