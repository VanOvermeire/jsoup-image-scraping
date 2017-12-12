package com.github.vanovermeire.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    private Path path;

    public CsvWriter(String location) {
        path = Paths.get(location);
    }

    public void write(List<String> strings) {
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for(String s : strings) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Received an IOException", e);
        }
    }
}