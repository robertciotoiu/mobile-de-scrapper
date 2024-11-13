package com.robertciotoiu.service.extractor.listing;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ListingXPath {

    @Value("${listing.xpath}")
    private String listingClassNameXpath;

    @Value("${listingId.xpath}")
    private String listingIdXpath;

    @Value("${title.xpath}")
    private String titleXpath;

    @Value("${isNew.xpath}")
    private String isNewXpath;

    @Value("${postedDate.xpath}")
    private String postedDateXpath;

    @Value("${postedDate2nd.xpath}")
    private String postedDate2ndXpath;

    @Value("${postedDateAds.xpath}")
    private String postedDateAdsXpath;

    @Value("${url.xpath}")
    private String urlXpath;

    @Value("${imgUrl.xpath}")
    private String imgUrlXpath;

    @Value("${isElectric.xpath}")
    private String isElectricXpath;

    @Value("${regMilPow.xpath}")
    private String regMilPowXpath;

    @Value("${typeFuelGearboxHuDoors.xpath}")
    private String typeFuelGearboxHuDoorsXpath;

    @Value("${consumptionEmissions.xpath}")
    private String consumptionEmissionsXpath;

    @Value("${vehicleExtras1.xpath}")
    private String vehicleExtras1Xpath;

    @Value("${vehicleExtras2.xpath}")
    private String vehicleExtras2Xpath;

    @Value("${vehicleExtras3.xpath}")
    private String vehicleExtras3Xpath;

    @Value("${seller.xpath}")
    private String sellerXpath;

    @Value("${price.xpath}")
    private String priceXpath;

    @Value("${priceRating.xpath}")
    private String priceRatingXpath;

    @Value("${dealerName.xpath}")
    private String dealerNameXpath;

    @Value("${dealerRatingParent.xpath}")
    private String dealerRatingParentXpath;

    @Value("${dealerRating.xpath}")
    private String dealerRatingXpath;

    @Value("${dealerWithLogoName.xpath}")
    private String dealerWithLogoNameXpath;

    @Value("${dealerWithLogoRatingParent.xpath}")
    private String dealerWithLogoRatingParentXpath;

    @Value("${dealerWithLogoRating.xpath}")
    private String dealerWithLogoRatingXpath;

    public static String LISTING_CLASS_NAME_XPATH;
    public static String LISTING_ID_XPATH;
    public static String TITLE_XPATH;
    public static String IS_NEW_XPATH;
    public static String POSTED_DATE_XPATH;
    public static String POSTED_DATE_2ND_XPATH;
    public static String POSTED_DATE_ADS_XPATH;
    public static String URL_XPATH;
    public static String IMG_URL_XPATH;
    public static String IS_ELECTRIC_XPATH;
    public static String REG_MIL_POW_XPATH;
    public static String TYPE_FUEL_GEARBOX_HU_DOORS_XPATH;
    public static String CONSUMPTION_EMISSIONS_XPATH;
    public static String VEHICLE_EXTRAS_1_XPATH;
    public static String VEHICLE_EXTRAS_2_XPATH;
    public static String VEHICLE_EXTRAS_3_XPATH;
    public static String SELLER_XPATH;
    public static String PRICE_XPATH;
    public static String PRICE_RATING_XPATH;
    public static String DEALER_NAME_XPATH;
    public static String DEALER_RATING_PARENT_XPATH;
    public static String DEALER_RATING_XPATH;
    public static String DEALER_WITH_LOGO_NAME_XPATH;
    public static String DEALER_WITH_LOGO_RATING_PARENT_XPATH;
    public static String DEALER_WITH_LOGO_RATING_XPATH;

    @PostConstruct
    private void init() {
        LISTING_CLASS_NAME_XPATH = listingClassNameXpath;
        LISTING_ID_XPATH = listingIdXpath;
        TITLE_XPATH = titleXpath;
        IS_NEW_XPATH = isNewXpath;
        POSTED_DATE_XPATH = postedDateXpath;
        POSTED_DATE_2ND_XPATH = postedDate2ndXpath;
        POSTED_DATE_ADS_XPATH = postedDateAdsXpath;
        URL_XPATH = urlXpath;
        IMG_URL_XPATH = imgUrlXpath;
        IS_ELECTRIC_XPATH = isElectricXpath;
        REG_MIL_POW_XPATH = regMilPowXpath;
        TYPE_FUEL_GEARBOX_HU_DOORS_XPATH = typeFuelGearboxHuDoorsXpath;
        CONSUMPTION_EMISSIONS_XPATH = consumptionEmissionsXpath;
        VEHICLE_EXTRAS_1_XPATH = vehicleExtras1Xpath;
        VEHICLE_EXTRAS_2_XPATH = vehicleExtras2Xpath;
        VEHICLE_EXTRAS_3_XPATH = vehicleExtras3Xpath;
        SELLER_XPATH = sellerXpath;
        PRICE_XPATH = priceXpath;
        PRICE_RATING_XPATH = priceRatingXpath;
        DEALER_NAME_XPATH = dealerNameXpath;
        DEALER_RATING_PARENT_XPATH = dealerRatingParentXpath;
        DEALER_RATING_XPATH = dealerRatingXpath;
        DEALER_WITH_LOGO_NAME_XPATH = dealerWithLogoNameXpath;
        DEALER_WITH_LOGO_RATING_PARENT_XPATH = dealerWithLogoRatingParentXpath;
        DEALER_WITH_LOGO_RATING_XPATH = dealerWithLogoRatingXpath;
    }

    private ListingXPath() {
        throw new IllegalStateException("Static class");
    }
}