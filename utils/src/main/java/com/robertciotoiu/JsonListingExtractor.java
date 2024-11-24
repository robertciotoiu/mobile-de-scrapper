package com.robertciotoiu;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsonListingExtractor {
    public static String extractInitialState(String filePath) {
        try {
            // Parse the HTML file
            Document doc = Jsoup.parse(new File(filePath), "UTF-8");
            // Select the script tag containing window.__INITIAL_STATE__
            Elements scriptElements = doc.select("script");
            for (Element scriptElement : scriptElements) {
                if (scriptElement.html().contains("window.__INITIAL_STATE__")) {
                    // Extract the JSON data
                    String scriptContent = scriptElement.html();
                    int startIndex = scriptContent.indexOf("window.__INITIAL_STATE__ = ") + "window.__INITIAL_STATE__ = ".length();
                    int endIndex = scriptContent.indexOf("window.__PUBLIC_CONFIG__", startIndex) + 1;
                    return scriptContent.substring(startIndex, endIndex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray extractSearchResults(String initialStateJson) {
        JSONArray searchResults = null;
        JSONTokener tokener = new JSONTokener(initialStateJson);
        JSONObject jsonObject = new JSONObject(tokener);
        searchResults = jsonObject.getJSONObject("search")
                .getJSONObject("srp")
                .getJSONObject("data")
                .getJSONObject("searchResults")
                .getJSONArray("items");
        return searchResults;
    }

    public static void main(String[] args) {
        String filePath = "utils/src/main/resources/vw-polo-201124.html";
        String initialStateJson = extractInitialState(filePath);
//        System.out.println(initialStateJson);

        JSONArray items = extractSearchResults(initialStateJson);
        // Iterate through the JSONArray
        for (int i = 0; i < items.length(); i++) {
            JSONObject jsonObject = items.getJSONObject(i);
            if(!jsonObject.has("id"))
                continue;
            String title = jsonObject.getString("title");
            System.out.println(title);
        }
    }
}
