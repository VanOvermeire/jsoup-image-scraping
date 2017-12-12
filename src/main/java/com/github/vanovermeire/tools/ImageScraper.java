package com.github.vanovermeire.tools;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class ImageScraper {

    private Document currentDocument;

    public ImageScraper(Document document) {
        this.currentDocument = document;
    }

    public Map<String, List<String>> getResults() {
        Map<String, List<String>> emperorsWithUrls = new HashMap<>();

        for(Element e : getRowResultDocDivs()) {
            String front = e.getElementsByClass("dl-horizontal").get(0).getElementsByTag("dd").get(3).text();
            Optional<Emperor> emperorsOptional = Emperor.getEmperor(front);

            if(emperorsOptional.isPresent()) {
                String emperor = emperorsOptional.get().toString();
                log.debug("Emperor {} mapped to {}", emperor, front);

                List<String> imageUrls = e.getElementsByClass("col-md-5 col-lg-4 pull-right").get(0)
                        .getElementsByClass("thumbImage")
                        .stream()
                        .filter(t -> t.attr("title").contains("Obverse"))
                        .map(t -> t.attr("href"))
                        .collect(Collectors.toList());

                if(!emperorsWithUrls.containsKey(emperor)) {
                    emperorsWithUrls.put(emperor, imageUrls);
                } else {
                    List<String> alreadyPresent = emperorsWithUrls.get(emperor);
                    imageUrls.addAll(alreadyPresent);
                    emperorsWithUrls.put(emperor, imageUrls);
                }
            } else {
                log.info("No emperor present: {}", front);
            }
        }

        return emperorsWithUrls;
    }

    private Elements getRowResultDocDivs() {
        return currentDocument.body()
                .getElementsByClass("row result-doc"); // all individual results
    }
}
