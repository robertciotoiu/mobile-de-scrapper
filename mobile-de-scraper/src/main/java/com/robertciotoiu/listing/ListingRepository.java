package com.robertciotoiu.listing;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends MongoRepository<Listing, ObjectId> {
    List<Listing> findByListingOriginalIdOrderByScrapeTimeDesc(String listingOriginalId);
}
