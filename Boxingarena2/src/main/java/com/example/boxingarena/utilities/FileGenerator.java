package com.example.boxingarena.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {

    private FileGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static void generateFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
