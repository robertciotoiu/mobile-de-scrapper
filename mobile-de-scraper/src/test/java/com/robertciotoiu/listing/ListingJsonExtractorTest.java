package com.robertciotoiu.listing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ListingJsonExtractorTest {

    private final ListingJsonExtractor extractor = new ListingJsonExtractor();

    private final String listingsJson = "{...}"; // Replace with the provided JSON data

    @Test
    public void testExtractListings() {
        List<ListingV2> listings = extractor.extract(listingsJson);
        assertNotNull(listings);
        assertFalse(listings.isEmpty());
    }

    @Test
    public void testExtractFirstListing() {
        List<ListingV2> listings = extractor.extract(listingsJson);
        ListingV2 firstListing = listings.get(0);

        assertEquals("408406884", firstListing.getId());
        assertEquals("Volkswagen", firstListing.getMake());
        assertEquals("Polo", firstListing.getModel());
        assertEquals("Car", firstListing.getSegment());
        assertEquals("€19,900", firstListing.getGrossCurrency());
        assertEquals(19900, firstListing.getGrossAmount());
        assertEquals("DE", firstListing.getCountryCode());
        assertEquals("59757 Arnsberg", firstListing.getSellerLocation());
        assertEquals("Ze Autohandel GmbH", firstListing.getDealerName());
        assertEquals(4.8, firstListing.getDealerRatingScore());
        assertEquals(28, firstListing.getDealerRatingCount());
    }

    @Test
    public void testExtractSecondListing() {
        List<ListingV2> listings = extractor.extract(listingsJson);
        ListingV2 secondListing = listings.get(1);

        assertEquals("409290910", secondListing.getId());
        assertEquals("Volkswagen", secondListing.getMake());
        assertEquals("Polo", secondListing.getModel());
        assertEquals("Car", secondListing.getSegment());
        assertEquals("€19,499", secondListing.getGrossCurrency());
        assertEquals(19499, secondListing.getGrossAmount());
        assertEquals("DE", secondListing.getCountryCode());
        assertEquals("46562 Voerde", secondListing.getSellerLocation());
        assertEquals("CarDeal 24", secondListing.getDealerName());
        assertEquals(4.6, secondListing.getDealerRatingScore());
        assertEquals(20, secondListing.getDealerRatingCount());
    }
}