package com.github.vanovermeire.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UrlPage {

    private String current;
    private int index;
    private int resultsPerPage;

    public UrlPage(String startUrl) {
        this.current = startUrl;
        this.index = 0;
        this.resultsPerPage = 20;
    }

    public void moveToNextPage() {
        if(current.contains("start=")) {
            current = current.replace("start=" + index, "start=" + (index + resultsPerPage));
            index +=resultsPerPage;
        } else {
            throw new IllegalStateException("Could not move. Check the URL page options for moving to next page.");
        }

    }

    public String getCurrentPageUrl() {
        return current;
    }

    public Document getPageAsDocument() {
        try {
            return Jsoup.connect(current).get();
        } catch (IOException e) {
            throw new RuntimeException("Could not connect to page: "+ e);
        }
    }

    public UrlPage withStartUrl(String startUrl) {
        this.current = startUrl;
        return this;
    }

    public UrlPage withResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
        return this;
    }
}
