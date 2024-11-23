package com.robertciotoiu.listing;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "listings_v2")
public class ListingV2 {

    @Id
    private String id;
    private String make;
    private String model;
    private String attributes;
    private boolean isDamaged;
    private String countryCode;
    private String zipCode;
    private String cityName;
    private String firstRegister;
    private String power;
    private String fuelType;
    private String mileage;
    private String cubicCapacity;
    private String transmissionType;
    private String attrGi;
    private String color;
    private String doorCount;
    private String seatCount;
    private String carType;
    private String emissionCategory;
    private String attrPvo;
    private long grossAmount;
    private long newAmount;
    private String grossCurrency;
    private String vat;
    private String title;
    private String subTitle;
    private String shortTitle;
    private String segment;
    private String listingType;
    private String thumbnailsUrl;
    private String category;
    private long sellerId;
    private String typeLocalized;
    private String dealerName;
    private double dealerRatingScore;
    private int dealerRatingCount;
    private String sellerLocation;
    private String sellerType;
    private String country;
    private LocalDateTime scrapeTime;
}