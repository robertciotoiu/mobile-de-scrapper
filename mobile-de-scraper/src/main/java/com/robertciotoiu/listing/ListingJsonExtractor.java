package com.robertciotoiu.listing;

import com.robertciotoiu.util.ListingIntegrityChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListingJsonExtractor {
    private static final Logger logger = LogManager.getLogger(ListingJsonExtractor.class);


    public List<Listing> extract(String listingsJson, String carSpecPageUrl) {
        var jsonArray = extractJsonArray(listingsJson);
        var listings = new ArrayList<Listing>();
        // Extract each listing from the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if(jsonArray.getJSONObject(i).optString("id").isEmpty()) {
                    continue;
                }
                var listing = extractListing(jsonArray.getJSONObject(i), carSpecPageUrl);
                listings.add(listing);
            } catch (Exception e) {
                logger.error("Error extracting listing", e);
            }
        }
        return listings;
    }

    private Listing extractListing(JSONObject jsonObject, String carSpecPageUrl) {
        var priceRating = getPriceRating(jsonObject);
        var attributes = getAttributes(jsonObject);
        var priceData = getPriceData(jsonObject);
        var sellerData = getSellerData(jsonObject);
        var thumbnailsUrl = getThumbnailsUrl(jsonObject);
        var previewImageSrc = getPreviewImageSrc(jsonObject);

        var listing = Listing.builder()
                .listingOriginalId(jsonObject.optString("id"))
                .make(jsonObject.optString("make"))
                .model(jsonObject.optString("model"))
                .attributes(jsonObject.optString("attributes"))
                .isNew(jsonObject.optBooleanObject("isNew", null))
                .isDamaged(jsonObject.optBooleanObject("isDamaged", null))
                .countryCode(attributes.countryCode())
                .attrGi(attributes.attrGi())
                .zipCode(attributes.zipCode())
                .cityName(attributes.cityName())
                .firstRegister(attributes.firstRegister())
                .power(attributes.power())
                .fuelType(attributes.fuelType())
                .mileage(attributes.mileage())
                .cubicCapacity(attributes.cubicCapacity())
                .transmissionType(attributes.transmissionType())
                .attrGi(attributes.gi())
                .color(attributes.color())
                .doorCount(attributes.doorCount())
                .seatCount(attributes.seatCount())
                .carType(attributes.carType())
                .emissionCategory(attributes.emissionCategory())
                .attrPvo(attributes.pvo())
                .grossAmount(priceData.grossAmount())
                .netAmount(priceData.netAmount())
                .grossCurrency(priceData.grossCurrency())
                .vat(priceData.vat())
                .priceRating(priceRating.priceRating())
                .priceRatingLabel(priceRating.priceRatingLabel())
                .priceNoRatingReason(priceRating.priceNoRatingReason())
                .priceRatingLabels(priceRating.priceRatingLabels())
                .title(jsonObject.optString("title"))
                .subTitle(jsonObject.optString("subTitle"))
                .shortTitle(jsonObject.optString("shortTitle"))
                .segment(jsonObject.optString("segment"))
                .listingType(jsonObject.optString("type"))
                .thumbnailsUrl(thumbnailsUrl)
                .previewImageSrc(previewImageSrc)
                .category(jsonObject.optString("category"))
                .sellerId(jsonObject.optLongObject("sellerId", null))
                .sellerName(sellerData.sellerName())
                .sellerRatingScore(sellerData.sellerRatingScore())
                .sellerRatingCount(sellerData.sellerRatingCount())
                .sellerLocation(sellerData.sellerLocation())
                .sellerType(sellerData.sellerType())
                .sellerCountry(sellerData.sellerCountry())
                .typeLocalized(sellerData.typeLocalized())
                .scrapeTime(LocalDateTime.now())
                .url("https://suchen.mobile.de" + jsonObject.optString("relativeUrl"))
                .categoryUrl(carSpecPageUrl)
                .active(true)
                .build();

        listing.replaceSpecialBlankLinesFields();
        ListingIntegrityChecker.checkForMandatoryFields(listing);
        return listing;
    }

    private static String getPreviewImageSrc(JSONObject jsonObject) {
        var previewImage = jsonObject.optJSONObject("previewImage");
        var previewImageSrc = "";
        if (previewImage != null) {
            previewImageSrc = previewImage.optString("src");
        }
        return previewImageSrc;
    }

    private static SellerData getSellerData(JSONObject jsonObject) {
        var contactInfo = jsonObject.optJSONObject("contactInfo");
        var typeLocalized = "";
        var sellerName = "";
        var sellerLocation = "";
        Double sellerRatingScore = null;
        Integer sellerRatingCount = null;
        var sellerCountry = "";
        var sellerType = "";
        if (contactInfo != null) {
            typeLocalized = contactInfo.optString("typeLocalized");
            sellerName = contactInfo.optString("name");
            sellerLocation = contactInfo.optString("location");
            sellerCountry = contactInfo.optString("country");
            sellerType = contactInfo.optString("sellerType");
            var rating = contactInfo.optJSONObject("rating");
            if (rating != null) {
                sellerRatingScore = rating.optDoubleObject("score", null);
                sellerRatingCount = rating.optIntegerObject("count", null);
            }
        }
        return new SellerData(typeLocalized, sellerName, sellerLocation, sellerRatingScore, sellerRatingCount, sellerCountry, sellerType);
    }

    private record SellerData(String typeLocalized, String sellerName, String sellerLocation, Double sellerRatingScore,
                              Integer sellerRatingCount, String sellerCountry, String sellerType) {
    }

    private static List<String> getThumbnailsUrl(JSONObject jsonObject) {
        var previewThumbnails = jsonObject.optJSONArray("previewThumbnails");
        var thumbnailsUrl = new ArrayList<String>();
        if (previewThumbnails != null) {
            for (int j = 0; j < previewThumbnails.length(); j++) {
                var previewThumbnail = previewThumbnails.optJSONObject(j);
                var src = previewThumbnail.optString("src");
                thumbnailsUrl.add(src);
            }
        }
        return thumbnailsUrl;
    }

    private static PriceData getPriceData(JSONObject jsonObject) {
        var price = jsonObject.optJSONObject("price");
        Long grossAmount = null;
        Long netAmount = null;
        var grossCurrency = "";
        var vat = "";
        if (price != null) {
            grossAmount = price.optLongObject("grossAmount", null);
            netAmount = price.optLongObject("netAmount", null);
            grossCurrency = price.optString("grossCurrency");
            vat = price.optString("vat");
        }
        return new PriceData(grossAmount, netAmount, grossCurrency, vat);
    }

    private record PriceData(Long grossAmount, Long netAmount, String grossCurrency, String vat) {
    }

    private static Attributes getAttributes(JSONObject jsonObject) {
        JSONObject attr = jsonObject.optJSONObject("attr");
        var countryCode = "";
        var attrGi = "";
        var zipCode = "";
        var cityName = "";
        var firstRegister = "";
        var power = "";
        var fuelType = "";
        var mileage = "";
        var cubicCapacity = "";
        var transmissionType = "";
        var gi = "";
        var color = "";
        var doorCount = "";
        var seatCount = "";
        var carType = "";
        var emissionCategory = "";
        var pvo = "";
        if (attr != null) {
            countryCode = attr.optString("cn");
            attrGi = attr.optString("gi");
            zipCode = attr.optString("z");
            cityName = attr.optString("loc");
            firstRegister = attr.optString("fr");
            power = attr.optString("pw");
            fuelType = attr.optString("ft");
            mileage = attr.optString("ml");
            cubicCapacity = attr.optString("cc");
            transmissionType = attr.optString("tr");
            gi = attr.optString("gi");
            color = attr.optString("ecol");
            doorCount = attr.optString("door");
            seatCount = attr.optString("sc");
            carType = attr.optString("c");
            emissionCategory = attr.optString("emc");
            pvo = attr.optString("pvo");
        }
        return new Attributes(countryCode, attrGi, zipCode, cityName, firstRegister, power, fuelType, mileage, cubicCapacity,
                transmissionType, gi, color, doorCount, seatCount, carType, emissionCategory, pvo);
    }

    private record Attributes(String countryCode, String attrGi, String zipCode, String cityName, String firstRegister,
                              String power, String fuelType, String mileage, String cubicCapacity,
                              String transmissionType,
                              String gi, String color, String doorCount, String seatCount, String carType,
                              String emissionCategory, String pvo) {
    }

    private static PriceRating getPriceRating(JSONObject jsonObject) {
        var price = jsonObject.optJSONObject("priceRating");
        var priceRating = "";
        var priceRatingLabel = "";
        var priceNoRatingReason = "";
        var priceRatingLabels = new ArrayList<String>();
        if (price != null) {
            priceRating = price.optString("rating");
            priceRatingLabel = price.optString("ratingLabel");
            priceNoRatingReason = price.optString("noRatingReason");
            var priceRatingLabelsJsonArray = price.optJSONArray("thresholdLabels");
            if (priceRatingLabelsJsonArray != null) {
                for (int i = 0; i < priceRatingLabelsJsonArray.length(); i++) {
                    priceRatingLabels.add(priceRatingLabelsJsonArray.optString(i));
                }
            }
        }
        return new PriceRating(priceRating, priceRatingLabel, priceNoRatingReason, priceRatingLabels);
    }

    private record PriceRating(String priceRating, String priceRatingLabel, String priceNoRatingReason,
                               ArrayList<String> priceRatingLabels) {
    }

    public static JSONArray extractJsonArray(String listingsJson) {
        JSONArray searchResults;
        JSONTokener tokener = new JSONTokener(listingsJson);
        JSONObject jsonObject = new JSONObject(tokener);
        searchResults = jsonObject.getJSONObject("search")
                .getJSONObject("srp")
                .getJSONObject("data")
                .getJSONObject("searchResults")
                .getJSONArray("items");
        return searchResults;
    }
}
