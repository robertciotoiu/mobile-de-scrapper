package com.robertciotoiu.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingService {
    final ListingJsonExtractor listingJsonExtractor;
    final ListingPersistor listingPersistor;

    @Autowired
    public ListingService(ListingJsonExtractor listingJsonExtractor, ListingPersistor listingPersistor) {
        this.listingJsonExtractor = listingJsonExtractor;
        this.listingPersistor = listingPersistor;
    }

    public void scrapeAndIngestListings(String json, String carSpecPageUrl) {
        var listings = listingJsonExtractor.extract(json, carSpecPageUrl);
        listingPersistor.persist(listings);
    }
}
