package com.robertciotoiu.util;

import com.robertciotoiu.listing.ListingV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListingIntegrityChecker {

    private static final Logger logger = LoggerFactory.getLogger(ListingIntegrityChecker.class);

    public static void checkForMandatoryFields(ListingV2 listing) {
        if (listing.getId().isEmpty()){
            logger.warn("Listing ID is missing");
        }

        if (listing.getMake().isEmpty()){
            logger.warn("Listing make is missing");
        }

        if(listing.getModel().isEmpty()){
            logger.warn("Listing model is missing");
        }

        if(listing.getCountryCode().isEmpty()){
            logger.warn("Listing country code is missing");
        }

        if(listing.getZipCode().isEmpty()){
            logger.warn("Listing zip code is missing");
        }

        if(listing.getCityName().isEmpty()){
            logger.warn("Listing city name is missing");
        }

        if(listing.getFirstRegister().isEmpty()){
            logger.warn("Listing first register is missing");
        }

        if(listing.getPower().isEmpty()){
            logger.warn("Listing power is missing");
        }

        if(listing.getFuelType().isEmpty()){
            logger.warn("Listing fuel type is missing");
        }

        if(listing.getMileage().isEmpty()){
            logger.warn("Listing mileage is missing");
        }

        if(listing.getCubicCapacity().isEmpty()){
            logger.warn("Listing cubic capacity is missing");
        }

        if(listing.getTransmissionType().isEmpty()){
            logger.warn("Listing transmission type is missing");
        }

        if(listing.getColor().isEmpty()){
            logger.warn("Listing color is missing");
        }

        if(listing.getDoorCount().isEmpty()){
            logger.warn("Listing door count is missing");
        }

        if(listing.getSeatCount().isEmpty()){
            logger.warn("Listing seat count is missing");
        }

        if(listing.getCarType().isEmpty()){
            logger.warn("Listing car type is missing");
        }

        if(listing.getEmissionCategory().isEmpty()){
            logger.warn("Listing emission category is missing");
        }

        if(listing.getAttrPvo().isEmpty()){
            logger.warn("Listing attr PVO is missing");
        }

        if(listing.getGrossAmount() <= 0){
            logger.warn("Listing gross amount is missing");
        }

        if(listing.getNetAmount() <= 0){
            logger.warn("Listing net amount is missing");
        }

        if(listing.getGrossCurrency().isEmpty()){
            logger.warn("Listing gross currency is missing");
        }

        if(listing.getVat().isEmpty()){
            logger.warn("Listing VAT is missing");
        }

        if(listing.getPriceRating().isEmpty()){
            logger.warn("Listing price rating is missing");
        }

        if(listing.getPriceRatingLabel().isEmpty()){
            logger.warn("Listing price rating label is missing");
        }

        if(listing.getPriceRatingLabels().isEmpty()){
            logger.warn("Listing price rating labels are missing");
        }

        if(listing.getPriceNoRatingReason().isEmpty()){
            logger.warn("Listing price no rating reason is missing");
        }

        if(listing.getTitle().isEmpty()){
            logger.warn("Listing title is missing");
        }

        if(listing.getSubTitle().isEmpty()){
            logger.warn("Listing sub title is missing");
        }

        if(listing.getShortTitle().isEmpty()){
            logger.warn("Listing short title is missing");
        }

        if(listing.getSegment().isEmpty()){
            logger.warn("Listing segment is missing");
        }

        if(listing.getListingType().isEmpty()){
            logger.warn("Listing type is missing");
        }

        if(listing.getThumbnailsUrl().isEmpty()){
            logger.warn("Listing thumbnails URL is missing");
        }

        if(listing.getCategory().isEmpty()){
            logger.warn("Listing category is missing");
        }

        if(listing.getTypeLocalized().isEmpty()){
            logger.warn("Listing type localized is missing");
        }

        if(listing.getSellerName().isEmpty()){
            logger.warn("Listing seller name is missing");
        }

        if(listing.getSellerLocation().isEmpty()){
            logger.warn("Listing seller location is missing");
        }

        if(listing.getSellerType().isEmpty()){
            logger.warn("Listing seller type is missing");
        }
    }
}
