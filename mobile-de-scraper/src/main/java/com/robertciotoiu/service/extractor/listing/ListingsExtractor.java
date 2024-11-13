package com.robertciotoiu.service.extractor.listing;

import com.robertciotoiu.data.model.FlatListing;
import com.robertciotoiu.data.model.Listing;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.robertciotoiu.service.extractor.listing.ListingXPath.LISTING_CLASS_NAME_XPATH;

@Component
public class ListingsExtractor {
    final ListingElementExtractor listingElementExtractor;

    @Autowired
    public ListingsExtractor(ListingElementExtractor listingElementExtractor) {
        this.listingElementExtractor = listingElementExtractor;
    }

    public List<FlatListing> extract(Document carSpecPage, String carSpecPageUrl) {
        var listings = new ArrayList<FlatListing>();
        var listingsElements = carSpecPage.select(LISTING_CLASS_NAME_XPATH);
        var carCategory = extractCarCategory(carSpecPageUrl);

        for (var listingElement : listingsElements) {
            var listing = extractListing(listingElement);
            listing.setCategory(carCategory);
            listing.setScrapeTime(LocalDateTime.now(ZoneOffset.UTC));
            listings.add(listing);
        }

        return listings;
    }

    private FlatListing extractListing(Element listingElement) {
        return listingElementExtractor.extract(listingElement);
    }

    private String extractCarCategory(String carSpecPageUrl) {
        var beginIndex = carSpecPageUrl.lastIndexOf("/") + 1;
        var endIndex = carSpecPageUrl.indexOf(".html?");
        return carSpecPageUrl.substring(beginIndex, endIndex);
    }
}
