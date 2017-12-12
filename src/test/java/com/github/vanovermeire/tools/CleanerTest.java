package com.github.vanovermeire.tools;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CleanerTest {

    @Test
    public void testCleanDateAD() {
        String date = "AD 54";

        int result = Cleaner.cleanDate(date);

        assertThat(result).isEqualTo(54);
    }

    @Test
    public void testCleanDateBC() {
        String date = "BC 54";

        int result = Cleaner.cleanDate(date);

        assertThat(result).isEqualTo(-54);
    }

    @Test
    public void testDifferentDateBC() {
        String date = "25 BC";

        int result = Cleaner.cleanDate(date);

        assertThat(result).isEqualTo(-25);
    }

    @Test
    public void testDateRange() {
        String date = "AD 64 - AD 68";

        int result = Cleaner.cleanDate(date);

        assertThat(result).isEqualTo(64);
    }

    @Test
    public void testUnsureDate() {
        String date = "AD 241?";

        int result = Cleaner.cleanDate(date);

        assertThat(result).isEqualTo(241);
    }

    @Test
    public void testCleanTextOneComma() {
        String text = "NERO CAES AVG IMP: Head of Nero, bare, right";

        String result = Cleaner.cleanText(text);

        assertThat(result).isEqualTo("nero caes avg imp head of nero bare right");
    }

    @Test
    public void testCleanTextMultipleCommas() {
        String text = "NERO CAES AVG IMP: Head, of, Nero, bare, right";

        String result = Cleaner.cleanText(text);

        assertThat(result).isEqualTo("nero caes avg imp head of nero bare right");
    }
}
