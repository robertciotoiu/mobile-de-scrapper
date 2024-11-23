package com.robertciotoiu;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CarBrandSpaceReplacer {
    public static void main(String[] args) {
        String inputFilePath = "utils/src/main/resources/mobile-de-car-brands.csv";
        String outputFilePath = "utils/src/main/resources/mobile-de-car-brands-hyphens.csv";
        replaceSpacesInCarBrands(inputFilePath, outputFilePath);
    }

    public static void replaceSpacesInCarBrands(String inputFilePath, String outputFilePath) {
        try {
            // Read the CSV file content
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath))).toLowerCase();
            // Split the content by commas to get individual car brands
            String[] carBrands = content.split(",");
            // Replace spaces with hyphens in each car brand
            List<String> modifiedCarBrands = new ArrayList<>();
            for (String brand : carBrands) {
                modifiedCarBrands.add(brand.replace(" ", "-"));
            }
            // Write the modified car brands to a new CSV file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
                writer.write(String.join(",", modifiedCarBrands));
            }
            System.out.println("Car brands with spaces replaced by hyphens saved to new CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}