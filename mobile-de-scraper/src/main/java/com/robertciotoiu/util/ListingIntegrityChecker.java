package com.robertciotoiu.util;

import com.robertciotoiu.listing.Listing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListingIntegrityChecker {

    private static final Logger logger = LoggerFactory.getLogger(ListingIntegrityChecker.class);

    public static void checkForMandatoryFields(Listing listing) {
        if (listing == null) {
            logger.warn("Listing is null");
            return;
        }

        if (listing.getListingOriginalId() == null || listing.getListingOriginalId().isEmpty()) {
            logger.warn("Listing ID is missing");
            return;
        }

        if (listing.getMake().isEmpty()) {
            logger.warn("Listing make is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getModel().isEmpty()) {
            logger.warn("Listing model is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getCountryCode().isEmpty()) {
            logger.warn("Listing country code is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getZipCode().isEmpty()) {
            logger.warn("Listing zip code is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getCityName().isEmpty()) {
            logger.warn("Listing city name is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getFirstRegister().isEmpty()) {
            logger.warn("Listing first register is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getPower().isEmpty()) {
            logger.warn("Listing power is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getFuelType().isEmpty()) {
            logger.warn("Listing fuel type is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getMileage().isEmpty()) {
            logger.warn("Listing mileage is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getCubicCapacity().isEmpty()) {
            logger.warn("Listing cubic capacity is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getTransmissionType().isEmpty()) {
            logger.warn("Listing transmission type is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getColor().isEmpty()) {
            logger.warn("Listing color is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getDoorCount().isEmpty()) {
            logger.warn("Listing door count is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }

        if (listing.getSeatCount().isEmpty()) {
            logger.warn("Listing seat count is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getCarType().isEmpty()) {
            logger.warn("Listing car type is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getEmissionCategory().isEmpty()) {
            logger.warn("Listing emission category is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getAttrPvo().isEmpty()) {
            logger.warn("Listing attr PVO is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getGrossAmount() <= 0) {
            logger.warn("Listing gross amount is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*
        if (listing.getNetAmount() <= 0) {
            logger.warn("Listing net amount is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }
*/
/*
        if (listing.getVat().isEmpty()) {
            logger.warn("Listing VAT is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getGrossCurrency().isEmpty()) {
            logger.warn("Listing gross currency is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getPriceRating().isEmpty()) {
            logger.warn("Listing price rating is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getPriceRatingLabel().isEmpty()) {
            logger.warn("Listing price rating label is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getPriceNoRatingReason().isEmpty()) {
            logger.warn("Listing price no rating reason is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getTitle().isEmpty()) {
            logger.warn("Listing title is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getSubTitle().isEmpty()) {
            logger.warn("Listing sub title is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getShortTitle().isEmpty()) {
            logger.warn("Listing short title is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getSegment().isEmpty()) {
            logger.warn("Listing segment is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getListingType().isEmpty()) {
            logger.warn("Listing type is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getThumbnailsUrl().isEmpty()) {
            logger.warn("Listing thumbnails URL is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

/*        if (listing.getPreviewImageSrc().isEmpty()) {
            logger.warn("Listing preview image source is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getCategory().isEmpty()) {
            logger.warn("Listing category is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getTypeLocalized().isEmpty()) {
            logger.warn("Listing type localized is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

/*        if (listing.getSellerName().isEmpty()) {
            logger.warn("Listing seller name is missing for ID: {} and URL: {}", listing.getId(), listing.getUrl());
        }*/

        if (listing.getSellerLocation().isEmpty()) {
            logger.warn("Listing seller location is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }

        if (listing.getSellerType().isEmpty()) {
            logger.warn("Listing seller type is missing for ID: {} and URL: {}", listing.getListingOriginalId(), listing.getUrl());
        }
    }
}
