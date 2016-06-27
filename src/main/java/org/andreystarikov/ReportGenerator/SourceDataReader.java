package org.andreystarikov.ReportGenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SourceDataReader {

    public ArrayList<String[]> readSourceData(String fileName) {
        ArrayList<String[]> allRows = null;
        try (BufferedReader Reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-16"))) {
            String line;
            while ((line = Reader.readLine()) != null) {
                String[] row = line.split("\t");
                if (allRows == null) {
                    allRows = new ArrayList<>();
                }
                allRows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRows;
    }
}

