package com.robertciotoiu.listing;

public class ListingExtractionException extends RuntimeException {
    public ListingExtractionException(String message) {
        super(message);
        System.exit(1);
    }
}
