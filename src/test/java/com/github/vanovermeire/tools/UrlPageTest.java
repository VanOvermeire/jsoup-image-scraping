package com.github.vanovermeire.tools;

import com.github.vanovermeire.tools.UrlPage;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class UrlPageTest {

    private final String startUrl = "http://numismatics.org/ocre/results?q=fulltext%20portrait_facet%3A%22Nero%22&start=0&lang=nl";

    private UrlPage urlPage;

    @Before
    public void setup() {
        urlPage = new UrlPage(startUrl);
    }

    @Test
    public void testGetCurrentPageShouldBeStart() {
        String currentPage = urlPage.getCurrentPageUrl();

        assertThat(currentPage).isEqualTo(startUrl);
    }

    @Test
    public void testGetNextPage() {
        urlPage.moveToNextPage();
        String currentPage = urlPage.getCurrentPageUrl();

        assertThat(currentPage).contains("start=20");
    }

    @Test
    public void testGetNextPageUnknownPagingSystem() {
        UrlPage fakePage = new UrlPage("http://fake.com");

        Throwable thrown = catchThrowable(fakePage::moveToNextPage);
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }
}
