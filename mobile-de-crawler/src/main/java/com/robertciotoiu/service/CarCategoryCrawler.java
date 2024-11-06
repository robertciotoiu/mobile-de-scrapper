package com.robertciotoiu.service;

import com.robertciotoiu.connection.JsoupWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarCategoryCrawler {
    private static final Logger logger = LogManager.getLogger(CarCategoryCrawler.class);
    private final JsoupWrapper jsoupWrapper;
    private final CarCategoryBaseUrlExtractor carCategoryBaseUrlExtractor;

    @Autowired
    public CarCategoryCrawler(JsoupWrapper jsoupWrapper, CarCategoryBaseUrlExtractor carCategoryBaseUrlExtractor) {
        this.jsoupWrapper = jsoupWrapper;
        this.carCategoryBaseUrlExtractor = carCategoryBaseUrlExtractor;
    }

    public List<String> crawl(String url) {
        ArrayList<String> carCategoryUrls = new ArrayList<>();
        try {
            Document doc = jsoupWrapper.getHtml(url);
            Elements links = doc.select("loc");

            for (Element link : links) {
                if(link.text().contains("sitemap-pls-carspecification") || link.text().contains("sitemap-pls-region-car")) {
                    carCategoryUrls.addAll(extractCarCategoryUrls(link.text()));
                }
            }
        } catch (IOException e) {
            logger.error("Cannot connect to mobile.de sitemap", e);
        }
        return carCategoryUrls;
    }

    private List<String> extractCarCategoryUrls(String url) {
        try {
            return carCategoryBaseUrlExtractor.extract(url);
        } catch (IOException e) {
            logger.error("Cannot connect to mobile.de sitemap", e);
        }
        return new ArrayList<>();
    }
}
