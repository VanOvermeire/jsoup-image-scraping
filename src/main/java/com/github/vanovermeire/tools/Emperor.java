package com.github.vanovermeire.tools;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Emperor {
    AUGUSTUS(Lists.newArrayList("Augustus", "Avgvstvs", "Octavian"), Lists.newArrayList("Nero", "Claudius")),
/*    TIBERIUS(Lists.newArrayList("Tiberius")),
    CALIGULA(Lists.newArrayList("Caligula")),
    CLAUDIUS(Lists.newArrayList("Claudius", "Claudivs")),*/
    NERO(Lists.newArrayList("Nero"), Lists.newArrayList());

    private List<String> names;
    private List<String> counterNames;

    Emperor(List<String> names, List<String> counterNames) {
        this.names = names;
        this.counterNames = counterNames;
    }

    public static Optional<Emperor> getEmperor(String input) {
        Optional<Emperor> first = Arrays.stream(values()).filter(e -> e.isEmperor(input)).findFirst();

        if(first.isPresent()) {
            return first;
        }

        return Optional.empty();
    }

    // even better: regexes
    public boolean isEmperor(String input) {
        final String text = input.toLowerCase();
        return names.stream().map(String::toLowerCase).anyMatch(text::contains)
                && counterNames.stream().map(String::toLowerCase).noneMatch(text::contains);
    }

    @Override
    public String toString() {
        return names.get(0);
    }


}
