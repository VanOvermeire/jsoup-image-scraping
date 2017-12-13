package com.github.vanovermeire.tools;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageScraperITCase {

    private static final String OUR_URL = "http://numismatics.org/ocre/results?q=authority_facet%3A%22Augustus%22&lang=nl";
    private UrlPage urlPage;

    @Before
    public void setup() {
        urlPage = new UrlPage(OUR_URL);
    }

    @Test
    public void test() {
        ImageScraper imageScraper = new ImageScraper(urlPage.getPageAsDocument());

        Map<String, List<String>> results = imageScraper.getResults();

        assertThat(results)
                .containsKeys(Emperor.AUGUSTUS.toString());

        List<String> strings = results.get(Emperor.AUGUSTUS.toString());

        assertThat(strings).allMatch(s ->
                s.endsWith(ImageSuffix.JPG.toString()) || s.endsWith(ImageSuffix.PNG.toString()));
    }
}
