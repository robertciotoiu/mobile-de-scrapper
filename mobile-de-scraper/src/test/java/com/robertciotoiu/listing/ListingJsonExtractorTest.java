package com.robertciotoiu.listing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ListingJsonExtractorTest {

    private final ListingJsonExtractor extractor;
    private String listingsJson;
    private String carSpecPageUrl;

    @Autowired
    public ListingJsonExtractorTest(ListingJsonExtractor extractor) {
        this.extractor = extractor;
    }

    @BeforeEach
    void setUp() throws IOException {
        listingsJson = new String(Files.readAllBytes(Paths.get("src/test/resources/initialStateSample.json")));
        carSpecPageUrl = "https://www.mobile.de/de/Auto/Volkswagen/Polo/400685894";
    }

    @Test
    public void testExtractListings() throws IOException {
        List<Listing> listings = extractor.extract(listingsJson, carSpecPageUrl);
        assertNotNull(listings);
        assertFalse(listings.isEmpty());
    }

    @Test
    public void testExtractFirstListing() {
        List<Listing> listings = extractor.extract(listingsJson, carSpecPageUrl);
        Listing firstListing = listings.get(0);

        assertEquals("400685894", firstListing.getListingOriginalId());
        assertEquals("Volkswagen", firstListing.getMake());
        assertEquals("Polo", firstListing.getModel());
        assertEquals("DE", firstListing.getCountryCode());
        assertEquals("27356", firstListing.getZipCode());
        assertEquals("Rotenburg", firstListing.getCityName());
        assertEquals("07/2020", firstListing.getFirstRegister());
        assertEquals("147 kW (200 hp)", firstListing.getPower());
        assertEquals("Petrol", firstListing.getFuelType());
        assertEquals("41,135 km", firstListing.getMileage());
        assertEquals("1,984 ccm", firstListing.getCubicCapacity());
        assertEquals("Automatic", firstListing.getTransmissionType());
        assertEquals("New", firstListing.getAttrGi());
        assertEquals("Black", firstListing.getColor());
        assertEquals("4/5", firstListing.getDoorCount());
        assertEquals("5", firstListing.getSeatCount());
        assertEquals("SmallCar", firstListing.getCarType());
        assertEquals("Euro6d-TEMP", firstListing.getEmissionCategory());
        assertEquals("1", firstListing.getAttrPvo());
        assertEquals(22990, firstListing.getGrossAmount());
        assertEquals(19319, firstListing.getNetAmount());
        assertEquals("EUR", firstListing.getGrossCurrency());
        assertEquals("19% VAT", firstListing.getVat());
        assertEquals("REASONABLE_PRICE", firstListing.getPriceRating());
        assertEquals("Fair price", firstListing.getPriceRatingLabel());
        assertEquals("", firstListing.getPriceNoRatingReason());
        assertEquals(List.of("€17,000", "€20,200", "€21,500", "€24,200", "€27,600", "€28,800"), firstListing.getPriceRatingLabels());
        assertEquals("Volkswagen Polo GTI 2.0 TSI OPF DSG LED-Scheinw. Navi PDC", firstListing.getTitle());
        assertEquals("GTI 2.0 TSI OPF DSG LED-Scheinw. Navi PDC", firstListing.getSubTitle());
        assertEquals("Volkswagen Polo", firstListing.getShortTitle());
        assertEquals("Car", firstListing.getSegment());
        assertEquals("topAd", firstListing.getListingType());
        assertEquals(List.of(
                "https://img.classistatic.de/api/v1/mo-prod/images/7f/7f5a5fbf-69a1-4b4b-87f0-98365671219a?rule=mo-160.jpg",
                "https://img.classistatic.de/api/v1/mo-prod/images/cf/cf8fe1d2-47a5-40ae-8e8d-bb13943c0377?rule=mo-160.jpg",
                "https://img.classistatic.de/api/v1/mo-prod/images/14/141636eb-5efe-440d-bff9-47579986e168?rule=mo-160.jpg"
        ), firstListing.getThumbnailsUrl());
        assertEquals("Small Car", firstListing.getCategory());
        assertEquals(455835, firstListing.getSellerId());
        assertEquals("Dealer", firstListing.getTypeLocalized());
        assertEquals("Autohaus Schmidt + Koch GmbH", firstListing.getSellerName());
        assertEquals(4.7, firstListing.getSellerRatingScore());
        assertEquals(30, firstListing.getSellerRatingCount());
        assertEquals("27356 Rotenburg", firstListing.getSellerLocation());
        assertEquals("DEALER", firstListing.getSellerType());
        assertEquals("DE", firstListing.getSellerCountry());
        assertNotNull(firstListing.getScrapeTime());
    }

    @Test
    public void liveTest() throws IOException {
        var json = new String(Files.readAllBytes(Paths.get("src/test/resources/0listinginvestigation.json")));
        List<ListingV2> listings = extractor.extract(json, carSpecPageUrl);
        assertNotNull(listings);
        assertFalse(listings.isEmpty());
    }
}