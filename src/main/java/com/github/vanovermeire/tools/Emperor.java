package com.github.vanovermeire.tools;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Emperor {
    AUGUSTUS("Augustus", Lists.newArrayList("Head of Augustus", "Head of Octavian")),
    TIBERIUS("Tiberius", Lists.newArrayList("Head of Tiberius")),
    CALIGULA("Caligula", Lists.newArrayList("Head of Caligula")),
    CLAUDIUS("Claudius", Lists.newArrayList("Head of Claudius")),
    NERO("Nero", Lists.newArrayList("Head of Nero"));

    private String name;
    private List<String> keywords;

    Emperor(String name, List<String> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    public static Optional<Emperor> getEmperor(String input) {
        return Arrays.stream(values()).filter(e -> e.isEmperor(input)).findFirst();
    }

    public boolean isEmperor(String input) {
        final String text = input.toLowerCase();
        return keywords.stream().map(String::toLowerCase).anyMatch(text::contains);
    }

    @Override
    public String toString() {
        return name;
    }
}