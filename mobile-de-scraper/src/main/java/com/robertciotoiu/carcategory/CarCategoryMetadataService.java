package com.robertciotoiu.carcategory;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarCategoryMetadataService {
    final CarCategoryMetadataRepository repository;
    final CarCategoryMetadataExtractor extractor;

    @Autowired
    public CarCategoryMetadataService(CarCategoryMetadataRepository repository, CarCategoryMetadataExtractor extractor) {
        this.repository = repository;
        this.extractor = extractor;
    }

    public void scrapeAndIngestCarCategoryMetadata(String json, String carSpecPageUrl) {
        if (isFirstPage(carSpecPageUrl)) {
            var carCategoryData = extractor.extract(json, carSpecPageUrl);
            repository.save(carCategoryData);
        }
    }

    public boolean isFirstPage(String carSpecPageUrl) {
        return !carSpecPageUrl.contains("pageNumber");
    }
}
