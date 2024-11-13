package com.robertciotoiu.data.repository;

import com.robertciotoiu.data.model.FlatListing;
import com.robertciotoiu.data.model.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends MongoRepository<FlatListing, String> {
}
