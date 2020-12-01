package com.ostach.advent;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
}