package com.robertciotoiu.listing;

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


    public List<ListingV2> extract(String listingsJson) {
        var jsonArray = extractJsonArray(listingsJson);
        var listings = new ArrayList<ListingV2>();
        // Extract each listing from the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            var listing = extractListing(jsonArray.getJSONObject(i));
            listings.add(listing);
        }
        return listings;
    }

    private ListingV2 extractListing(JSONObject jsonObject) {
        return ListingV2.builder()
                .id(jsonObject.optString("id"))
                .make(jsonObject.optString("make"))
                .model(jsonObject.optString("model"))
                .attributes(jsonObject.optString("attributes"))
                .isDamaged(jsonObject.optBoolean("isDamaged"))
                .countryCode(jsonObject.optString("attr.cn"))
                .zipCode(jsonObject.optString("attr.z"))
                .cityName(jsonObject.optString("attr.loc"))
                .firstRegister(jsonObject.optString("attr.fr"))
                .power(jsonObject.optString("attr.pw"))
                .fuelType(jsonObject.optString("attr.ft"))
                .mileage(jsonObject.optString("attr.ml"))
                .cubicCapacity(jsonObject.optString("attr.cc"))
                .transmissionType(jsonObject.optString("attr.tr"))
                .attrGi(jsonObject.optString("attr.gi"))
                .color(jsonObject.optString("attr.ecol"))
                .doorCount(jsonObject.optString("attr.door"))
                .seatCount(jsonObject.optString("attr.sc"))
                .carType(jsonObject.optString("attr.c"))
                .emissionCategory(jsonObject.optString("attr.emc"))
                .attrPvo(jsonObject.optString("attr.pvo"))
                .grossAmount(jsonObject.optLong("price.grossAmount"))
                .newAmount(jsonObject.optLong("price.newAmount"))
                .grossCurrency(jsonObject.optString("price.grossCurrency"))
                .vat(jsonObject.optString("price.vat"))
                .title(jsonObject.optString("title"))
                .subTitle(jsonObject.optString("subTitle"))
                .shortTitle(jsonObject.optString("shortTitle"))
                .segment(jsonObject.optString("segment"))
                .listingType(jsonObject.optString("type"))
                .thumbnailsUrl(jsonObject.optString("previewImage.src"))
                .category(jsonObject.optString("category"))
                .sellerId(jsonObject.optLong("sellerId"))
                .typeLocalized(jsonObject.optString("contactInfo.typeLocalized"))
                .dealerName(jsonObject.optString("contactInfo.name"))
                .dealerRatingScore(jsonObject.optJSONObject("contactInfo.rating").optDouble("score", 0.0))
                .dealerRatingCount(jsonObject.optJSONObject("contactInfo.rating").optInt("count", 0))
                .sellerLocation(jsonObject.optString("contactInfo.location"))
                .sellerType(jsonObject.optString("contactInfo.sellerType"))
                .country(jsonObject.optString("contactInfo.country"))
                .scrapeTime(LocalDateTime.now())
                .build();
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
