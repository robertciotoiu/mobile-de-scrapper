package com.robertciotoiu.carcategory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCategoryMetadataRepository extends MongoRepository<CarCategoryMetadata, String> {
}