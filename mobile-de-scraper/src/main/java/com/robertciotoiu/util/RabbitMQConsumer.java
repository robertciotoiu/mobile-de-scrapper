package com.robertciotoiu.util;

import com.rabbitmq.client.Channel;
import com.robertciotoiu.ScraperService;
import com.robertciotoiu.listing.ListingExtractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class RabbitMQConsumer {
    private static final Logger logger = LogManager.getLogger(RabbitMQConsumer.class);
    final ScraperService scraperService;

    @Autowired
    public RabbitMQConsumer(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}", concurrency = "1")
    public void handleMessage(String carCategoryUrl, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        try {
            logger.info("Received carCategoryUrl: {}", carCategoryUrl);
            scraperService.scrape(carCategoryUrl);
        } catch (Exception e) {
            // handle any exceptions
            logger.error("Exception caught while processing carCategoryUrl: {}", carCategoryUrl, e);
            // reject and requeue the carCategoryUrl
            channel.basicReject(deliveryTag, true);
            throw new ListingExtractionException("Stopping program execution... Check and solve the parsing exception.", e);
        } finally {
            // Acknowledge the carCategoryUrl to remove it from the queue
            channel.basicAck(deliveryTag, false);
        }
    }

}