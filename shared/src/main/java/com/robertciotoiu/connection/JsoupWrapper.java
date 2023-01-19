package com.robertciotoiu.connection;

import com.robertciotoiu.exception.MultithreadingNotAllowedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Instant;
import java.util.Random;

/**
 * Wrapper over Jsoup library.
 * Must be used for every access to a URL.
 * To use: extend this class and simply static call getHtml(url)
 * Ensures even in a multithreading environment the awaiting between HTTP requests.
 */
public class JsoupWrapper {
    private static final Random random = new Random();
    private static final Logger logger = LogManager.getLogger(JsoupWrapper.class);

    protected JsoupWrapper() {
        throw new IllegalStateException("Static class");
    }

    /**
     * Use this method for a responsible scraping.
     * It ensures to access a link every 2-5 seconds.
     *
     * @param url URL to access and extract its HTML
     * @return HTML of the page
     * @throws IOException when page is not accessible
     */
    public static Document getHtml(String url) throws IOException, MultithreadingNotAllowedException {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            String time = Instant.now().toString();
            long seconds = random.nextInt(2000, 5000);
            logger.info("HTTP get request at address: {} at UTC time: {}. Awaiting {} seconds until the next request", url, time, seconds);
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            logger.fatal("Sleep failed! Multithreading execution spotted. Stopping the application...", e);
            Thread.currentThread().interrupt();
            throw new MultithreadingNotAllowedException("Sleep failed! Multithreading execution spotted. Stopping the application...");
        }

        return doc;
    }
}
