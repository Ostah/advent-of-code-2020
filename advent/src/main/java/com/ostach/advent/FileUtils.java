package com.ostach.advent;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class FileUtils {

    public List<String> getFileLines(String path) {
        try {
            return IOUtils.readLines(getFileFromResourceAsStream(path), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public Stream<String> getLinesStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new BufferedReader(new InputStreamReader(inputStream)).lines();
        }
    }

    public char[][] getCharactersArray(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        try {
            Path path = Paths.get(url.toURI());
            return Files.lines(path)
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}