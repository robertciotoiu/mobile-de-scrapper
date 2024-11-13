package com.robertciotoiu.data.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "flat_listings")
public class FlatListing {
    @Id
    private String listingId;
    private String title;
    private String category;
    private LocalDateTime scrapeTime;
    private String url;
    private String imgUrl;
    private boolean isNew;
    private boolean isElectric;
    private LocalDateTime postedDate;
    private String registrationDate;
    private Integer mileage;
    private Integer kw;
    private Integer hp;
    private String type;
    private String status;
    private String fuel;
    private String gearbox;
    private String hu;
    private Integer doors;
    private Double consumption;
    private Double emissions;
    private String dealerName;
    private Double dealerRating;
    private Integer ratings;
    private String location;
    private String sellerType;
    private Double price;
    private String currency;
    private List<String> vehicleExtras;
}
