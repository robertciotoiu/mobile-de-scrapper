package com.robertciotoiu;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarBrandMobileWebsiteCheck {

    @Test
    public void testCarBrandWebsites() {
        String csvFilePath = "src/test/resources/mobile-de-car-brands-hyphens.csv";
        List<String> carBrands = readCarBrandsFromCsv(csvFilePath);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (String brand : carBrands) {
            String url = "https://suchen.mobile.de/auto/" + brand + ".html";
            driver.get(url);
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.toLowerCase().contains(brand.toLowerCase()), "URL: " + url + " did not load correctly. Page title: " + pageTitle);
        }

        driver.quit();
    }

    private List<String> readCarBrandsFromCsv(String filePath) {
        List<String> carBrands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] brands = line.split(",");
                for (String brand : brands) {
                    carBrands.add(brand.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading car brands from CSV file");
        }
        return carBrands;
    }
}