package com.robertciotoiu.data.model.listing;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collation = "listings")
public class Listing {
    @Id
    private String listingId;
    private String title;
    private String url;
    private String imgUrl;
    private boolean isNew;
    private boolean isElectric;
    private LocalDateTime postedDate;
    private RegMilPow regMilPow;
    private TypeStatusFuelGearboxHuDoors typeStatusFuelGearboxHuDoors;
    private ConsumptionEmissions consumptionEmissions;
    private List<String> vehicleExtras;
    private Seller seller;
    private PriceData priceData;
}
