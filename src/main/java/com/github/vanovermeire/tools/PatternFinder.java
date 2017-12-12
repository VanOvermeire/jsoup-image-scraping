package com.github.vanovermeire.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFinder {

    public static boolean indexOfPattern(String p, String s) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);

        return matcher.find();
    }
}
