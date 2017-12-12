package com.github.vanovermeire.tools;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoinageScraper {

    private Document currentDocument;

    public CoinageScraper(Document document) {
        this.currentDocument = document;
    }

    public List<Coin> getResultAsCoin() {
        return getAllResults().stream().map(e -> e.getElementsByTag("dd"))
                .filter(e -> e.size() >= 5) // ignore those with missing data
                .map(e -> Coin.builder()
                        .date(Cleaner.cleanDate(e.get(0).text()))
                        .denomination(Cleaner.cleanText(e.get(1).text()))
                        .mint(Cleaner.cleanText(e.get(2).text()))
                        .front(Cleaner.cleanText(e.get(3).text()))
                        .back(Cleaner.cleanText(e.get(4).text()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<Element> getAllResults() {
        return getRowResultDocDivs().stream()
                .map(e -> e.getElementsByClass(" dl-horizontal"))
                .map(e -> e.get(0))
                .collect(Collectors.toList());
    }

    private Elements getRowResultDocDivs() {
        return currentDocument.body().getElementsByClass("row result-doc");
    }
}
