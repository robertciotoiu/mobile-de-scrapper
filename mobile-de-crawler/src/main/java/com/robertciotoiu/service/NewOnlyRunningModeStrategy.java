package com.robertciotoiu.service;

import com.robertciotoiu.RabbitMQProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewOnlyRunningModeStrategy implements RunningModeStrategy {
    private static final Logger logger = LogManager.getLogger(NewOnlyRunningModeStrategy.class);
    private final RabbitMQProducer rabbitMQProducer;
    private final CarCategoryCrawler carCategoryCrawler;

    @Autowired
    public NewOnlyRunningModeStrategy(RabbitMQProducer rabbitMQProducer, CarCategoryCrawler carCategoryCrawler) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.carCategoryCrawler = carCategoryCrawler;
    }

    @Override
    public void execute(String url) {
        var urls = carCategoryCrawler.crawl(url);
        rabbitMQProducer.publishMessagesToRabbitMQ(urls);
    }
}
