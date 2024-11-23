package com.robertciotoiu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlCleaner {

    public String cleanHtml(String filePath) throws IOException {
        // Load the HTML file
        String htmlContent = Files.readString(Paths.get(filePath));

        // Parse the HTML content
        Document document = Jsoup.parse(htmlContent);

        // Remove unuseful tags
        removeUnusefulTags(document);

        return document.html();
    }

    private void removeUnusefulTags(Document document) {
        // List of tags to remove
        String[] tagsToRemove = {"script", "style", "iframe", "link", "meta", "svg", "noscript", "form", "input", "button", "select", "option", "textarea", "label", "fieldset", "legend", "optgroup", "datalist", "output", "progress", "meter", "object", "param", "embed", "canvas", "map", "area", "track", "audio", "video", "source"};

        for (String tag : tagsToRemove) {
            Elements elements = document.select(tag);
            for (Element element : elements) {
                element.remove();
            }
        }
    }

    public static void main(String[] args) {
        HtmlCleaner cleaner = new HtmlCleaner();
        try {
            String cleanedHtml = cleaner.cleanHtml("D:\\src\\IdeaProjects\\mobile-de-car-data-collector\\utils\\src\\main\\resources\\vw-polo-201124.html");
            // Save the cleaned HTML to to same file
            Files.writeString(Paths.get("D:\\src\\IdeaProjects\\mobile-de-car-data-collector\\utils\\src\\main\\resources\\vw-polo-201124-cleaned.html"), cleanedHtml);
            System.out.println(cleanedHtml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}