package com.robertciotoiu.service;

import com.robertciotoiu.CarCategoryParsableUrlExtractor;
import com.robertciotoiu.RabbitMQProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FullRunningModeStrategy implements RunningModeStrategy {
    private static final Logger logger = LogManager.getLogger(FullRunningModeStrategy.class);
    private final CarCategoryBaseUrlExtractor carCategoryBaseUrlExtractor;
    private final CarCategoryParsableUrlExtractor carCategoryParsableUrlExtractor;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public FullRunningModeStrategy(CarCategoryBaseUrlExtractor carCategoryBaseUrlExtractor, CarCategoryParsableUrlExtractor carCategoryParsableUrlExtractor, RabbitMQProducer rabbitMQProducer) {
        this.carCategoryBaseUrlExtractor = carCategoryBaseUrlExtractor;
        this.carCategoryParsableUrlExtractor = carCategoryParsableUrlExtractor;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void execute(String url) {
        try {
            var carCategoryBaseUrls = carCategoryBaseUrlExtractor.extract(url);
            carCategoryBaseUrls.forEach(carCategoryBaseUrl -> {
                var carCategoryParsableUrls = carCategoryParsableUrlExtractor.extractParsableUrls(carCategoryBaseUrl);
                rabbitMQProducer.publishMessagesToRabbitMQ(carCategoryParsableUrls);
            });
        } catch (IOException e) {
            logger.error("Cannot connect to mobile.de sitemap", e);
        }
    }
}