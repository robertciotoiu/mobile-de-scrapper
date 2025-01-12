package com.robertciotoiu.listing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ListingPersistor {
    private static final Logger logger = LogManager.getLogger(ListingPersistor.class);

    private final ListingRepository repository;

    @Autowired
    public ListingPersistor(ListingRepository repository) {
        this.repository = repository;
    }

    public void persist(List<Listing> listings) {
        int totalListings = listings.size();
        var listingsToPersist = listings.stream()
                .filter(this::shouldPersist)
                .toList();

        var savedListings = repository.saveAll(listingsToPersist);

        var failedListings = listings.stream()
                .filter(listing -> !savedListings.contains(listing))
                .toList();
        int totalFailedListings = failedListings.size();
        if (totalFailedListings > 0) {
            logger.error("{} out of {} objects failed to persist: {}", totalFailedListings, totalListings, failedListings);
        } else {
            logger.info("All {} listings persisted successfully.", totalListings);
        }
    }

    private boolean shouldPersist(Listing newListing) {
        var existingListings = repository.findFirstByListingOriginalIdOrderByScrapeTimeDesc(newListing.getListingOriginalId());
        if (existingListings.isEmpty()) {
            return true;
        }
        var mostRecentListing = existingListings.get(0);
        return !Objects.equals(mostRecentListing.getGrossAmount(), newListing.getGrossAmount()) ||
                !Objects.equals(mostRecentListing.getNetAmount(), newListing.getNetAmount()) ||
                !Objects.equals(mostRecentListing.getMake(), newListing.getMake()) ||
                !Objects.equals(mostRecentListing.getModel(), newListing.getModel()) ||
                !Objects.equals(mostRecentListing.getTitle(), newListing.getTitle()) ||
                !Objects.equals(mostRecentListing.getUrl(), newListing.getUrl()) ||
                !Objects.equals(mostRecentListing.getPreviewImageSrc(), newListing.getPreviewImageSrc());
    }
}
