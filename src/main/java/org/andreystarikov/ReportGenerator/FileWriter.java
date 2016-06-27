package org.andreystarikov.ReportGenerator;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileWriter {

    private final Charset UTF16 = Charset.forName("utf-16");

    public void saveFile(String str, String filename) throws Exception {
        Path logFile = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(logFile, UTF16)) {
            writer.write(str);
        }
    }

    public void saveFile(ArrayList<String> strings, String filename) throws Exception {
        Path logFile = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(logFile, UTF16)) {
            for (String str : strings)
                writer.write(str);
        }
    }
}
