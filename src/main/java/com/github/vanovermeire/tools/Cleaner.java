package com.github.vanovermeire.tools;

public class Cleaner {

    public static int cleanDate(String date) {
        String newDate = date;

        if(date.contains("-")) {
            newDate = newDate.substring(0, newDate.indexOf("-") - 1);
        }

        if(date.contains(" BC")) {
            newDate = "-" + newDate.substring(0, newDate.indexOf("BC") - 1);
        }

        newDate = newDate.replace("AD ", "");
        newDate = newDate.replace("BC ", "-");
        newDate = newDate.replace("?", "");

        return Integer.parseInt(newDate);
    }

    public static String cleanText(String text) {
        // easier for AWS if we remove the "," and ":"
        String newText = text;
        newText = newText.replaceAll(",", "")
                .replaceAll(":", "")
                .replaceAll(";", "")
                .replaceAll("\n", " ")
                .replaceAll("\t", " ")
                .toLowerCase()
                .trim();
        return newText;
    }
}