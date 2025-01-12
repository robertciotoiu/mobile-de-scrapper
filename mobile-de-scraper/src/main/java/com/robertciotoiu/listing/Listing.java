package com.robertciotoiu.listing;

import com.robertciotoiu.util.HtmlUtils;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "listings_v3")
@CompoundIndexes({
        @CompoundIndex(name = "listingOriginalId_scrapeTime_idx", def = "{'listingOriginalId': -1, 'scrapeTime': -1}")
})
public class Listing {

    @Id
    private ObjectId id;
    private String listingOriginalId;
    private String make;
    private String model;
    private String attributes;
    private Boolean isNew;
    private Boolean isDamaged;
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
    private Long grossAmount;
    private Long netAmount;
    private String grossCurrency;
    private String vat;
    private String priceRating;
    private String priceRatingLabel;
    private List<String> priceRatingLabels;
    private String priceNoRatingReason;
    private String title;
    private String subTitle;
    private String shortTitle;
    private String segment;
    private String listingType;
    private List<String> thumbnailsUrl;
    private String previewImageSrc;
    private String category;
    private Long sellerId;
    private String typeLocalized;
    private String sellerName;
    private Double sellerRatingScore;
    private Integer sellerRatingCount;
    private String sellerLocation;
    private String sellerType;
    private String sellerCountry;
    private LocalDateTime scrapeTime;
    private String url;
    private String categoryUrl;
    private boolean active; // Flag to indicate if the listing is active
    private LocalDateTime deactivationTime; // Time when the listing was deactivated - this is estimated based on when we checked it

    public void replaceSpecialBlankLinesFields() {
        this.make = HtmlUtils.replaceSpecialBlankLines(this.make);
        this.model = HtmlUtils.replaceSpecialBlankLines(this.model);
        this.attributes = HtmlUtils.replaceSpecialBlankLines(this.attributes);
        this.countryCode = HtmlUtils.replaceSpecialBlankLines(this.countryCode);
        this.zipCode = HtmlUtils.replaceSpecialBlankLines(this.zipCode);
        this.cityName = HtmlUtils.replaceSpecialBlankLines(this.cityName);
        this.firstRegister = HtmlUtils.replaceSpecialBlankLines(this.firstRegister);
        this.power = HtmlUtils.replaceSpecialBlankLines(this.power);
        this.fuelType = HtmlUtils.replaceSpecialBlankLines(this.fuelType);
        this.mileage = HtmlUtils.replaceSpecialBlankLines(this.mileage);
        this.cubicCapacity = HtmlUtils.replaceSpecialBlankLines(this.cubicCapacity);
        this.transmissionType = HtmlUtils.replaceSpecialBlankLines(this.transmissionType);
        this.attrGi = HtmlUtils.replaceSpecialBlankLines(this.attrGi);
        this.color = HtmlUtils.replaceSpecialBlankLines(this.color);
        this.doorCount = HtmlUtils.replaceSpecialBlankLines(this.doorCount);
        this.seatCount = HtmlUtils.replaceSpecialBlankLines(this.seatCount);
        this.carType = HtmlUtils.replaceSpecialBlankLines(this.carType);
        this.emissionCategory = HtmlUtils.replaceSpecialBlankLines(this.emissionCategory);
        this.attrPvo = HtmlUtils.replaceSpecialBlankLines(this.attrPvo);
        this.grossCurrency = HtmlUtils.replaceSpecialBlankLines(this.grossCurrency);
        this.vat = HtmlUtils.replaceSpecialBlankLines(this.vat);
        this.priceRating = HtmlUtils.replaceSpecialBlankLines(this.priceRating);
        this.priceRatingLabel = HtmlUtils.replaceSpecialBlankLines(this.priceRatingLabel);
        this.priceNoRatingReason = HtmlUtils.replaceSpecialBlankLines(this.priceNoRatingReason);
        this.title = HtmlUtils.replaceSpecialBlankLines(this.title);
        this.subTitle = HtmlUtils.replaceSpecialBlankLines(this.subTitle);
        this.shortTitle = HtmlUtils.replaceSpecialBlankLines(this.shortTitle);
        this.segment = HtmlUtils.replaceSpecialBlankLines(this.segment);
        this.listingType = HtmlUtils.replaceSpecialBlankLines(this.listingType);
        this.thumbnailsUrl = sanitizeThumbnails();
        this.previewImageSrc = HtmlUtils.replaceSpecialBlankLines(this.previewImageSrc);
        this.category = HtmlUtils.replaceSpecialBlankLines(this.category);
        this.typeLocalized = HtmlUtils.replaceSpecialBlankLines(this.typeLocalized);
        this.sellerName = HtmlUtils.replaceSpecialBlankLines(this.sellerName);
        this.sellerLocation = HtmlUtils.replaceSpecialBlankLines(this.sellerLocation);
        this.sellerType = HtmlUtils.replaceSpecialBlankLines(this.sellerType);
        this.sellerCountry = HtmlUtils.replaceSpecialBlankLines(this.sellerCountry);
        this.url = HtmlUtils.replaceSpecialBlankLines(this.url);
        this.url = HtmlUtils.replaceBackslash(this.url);
        this.categoryUrl = HtmlUtils.replaceSpecialBlankLines(this.categoryUrl);
    }

    private ArrayList<String> sanitizeThumbnails() {
        var sanitizedThumbnailUrls = new ArrayList<String>();
        for(var thumbnailUrl : this.thumbnailsUrl) {
            sanitizedThumbnailUrls.add(HtmlUtils.replaceSpecialBlankLines(thumbnailUrl));
        }
        return sanitizedThumbnailUrls;
    }
}