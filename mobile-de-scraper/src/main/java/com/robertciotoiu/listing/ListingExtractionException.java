package com.robertciotoiu.listing;

public class ListingExtractionException extends RuntimeException {
    public ListingExtractionException(String message, Exception e) {
        super(message, e);
        System.exit(1);
    }
}
