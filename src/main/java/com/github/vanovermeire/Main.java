package com.github.vanovermeire;

import com.github.vanovermeire.tools.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String OUR_URL = "http://numismatics.org/ocre/results?q=%28authority_facet%3A%22Augustus%22%20OR%20authority_facet%3A%22Nero%22%29&lang=nl&start=0";
    //"http://numismatics.org/ocre/results?q=%28authority_facet%3A%22Augustus%22+OR+authority_facet%3A%22Claudius%22+OR+authority_facet%3A%22Gaius%2FCaligula%22+OR+authority_facet%3A%22Nero%22+OR+authority_facet%3A%22Tiberius%22%29&start=0";
    private static final Integer PAGE_LIMIT = 62;

    public static void main(String[] args) throws IOException {
        UrlPage url = new UrlPage(OUR_URL);
        Map<String, List<String>> results = new HashMap<>();
        ImageDownloader imageDownloader = new ImageDownloader("images");

        int start = 0;

        while(start < PAGE_LIMIT) {
            System.out.println("Now on page " + url.getCurrentPageUrl());

            ImageScraper imageScraper = new ImageScraper(url.getPageAsDocument());
            Map<String, List<String>> imageScraperResults = imageScraper.getResults();

            imageScraperResults.forEach((key, value) -> {
                if(results.containsKey(key)) {
                    value.addAll(results.get(key));
                    results.put(key, value);
                } else {
                    results.put(key, value);
                }
            });

            url.moveToNextPage();

            start++;
        }

        // augustus 1000+ coins, nero 500+, so those two
        results.forEach((key, value) -> {
            System.out.println("(Total) Emperor " + key + " has " + value.size());
        });

        results.forEach((key, values) ->
                values.forEach(v -> imageDownloader.downloadImageToFolder(key, v)));
    }
}