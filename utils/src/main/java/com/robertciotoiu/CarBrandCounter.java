package com.robertciotoiu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarBrandCounter {
    public static void main(String[] args) {
        String csvFilePath = "utils/src/main/resources/mobile-de-car-brands.csv";
        int count = countCarBrands(csvFilePath);
        System.out.println("Number of car brands: " + count);
    }

    public static int countCarBrands(String filePath) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] brands = line.split(",");
                count += brands.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}