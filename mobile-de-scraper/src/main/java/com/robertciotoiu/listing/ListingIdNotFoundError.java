package com.robertciotoiu.listing;

public class ListingIdNotFoundError extends Error {
    public ListingIdNotFoundError(String message, Exception e) {
        super(message, e);
    }
}
