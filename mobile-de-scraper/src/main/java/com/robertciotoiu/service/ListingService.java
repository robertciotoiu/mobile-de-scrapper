package com.robertciotoiu.service;

import com.robertciotoiu.data.ListingPersistor;
import com.robertciotoiu.service.extractor.listing.ListingsExtractor;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingService {
    final ListingsExtractor listingsExtractor;
    final ListingPersistor listingPersistor;

    @Autowired
    public ListingService(ListingsExtractor listingsExtractor, ListingPersistor listingPersistor) {
        this.listingsExtractor = listingsExtractor;
        this.listingPersistor = listingPersistor;
    }

    public void scrapeAndIngestListings(Document carSpecPage, String carSpecPageUrl) {
        var listings = listingsExtractor.extract(carSpecPage, carSpecPageUrl);
        listingPersistor.persist(listings);
    }
}
