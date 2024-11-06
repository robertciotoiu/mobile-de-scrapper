package com.robertciotoiu.cooldown.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "operational_car_categories_cooldown")
public class CarCategoryCooldown {
    @Id
    String carCategoryUrl;
    LocalDateTime crawlTime;
    int cooldownMinutes;
}
