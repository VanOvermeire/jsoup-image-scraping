package com.github.vanovermeire.tools;

import java.util.Arrays;
import java.util.Optional;

public enum ImageSuffix {
    PNG(".png"),
    JPG(".jpg");

    private String name;

    ImageSuffix(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Optional<ImageSuffix> getImageSuffix(String input) {
        return Arrays.stream(ImageSuffix.values()).filter(i -> input.contains(i.toString())).findFirst();
    }
}
