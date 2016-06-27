package org.andreystarikov.ReportGenerator;

import java.util.ArrayList;

public class ReportGenerator {

    private static final String SETTINGS = "settings.xml";
    private static final String SOURCE_DATA = "source-data.tsv";
    private String defaultFile = "table.txt";
    private SettingsReader settingsReader;

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.report();
    }

    public void report() {
        setSettings(SETTINGS);
        ArrayList<String[]> data = setSourceData(SOURCE_DATA);
        ArrayList<String> table = new TableMaker(settingsReader).tableEditor(data);
        FileWriter fileWriter = new FileWriter();
        try {
            fileWriter.saveFile(table, defaultFile);
            System.out.println("Файл записан.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSettings(String fileName) {
        settingsReader = new SettingsReader();
        settingsReader.setSettings(fileName);
    }

    private ArrayList<String[]> setSourceData(String fileName) {
        SourceDataReader sourceDataReader = new SourceDataReader();
        return sourceDataReader.readSourceData(fileName);
    }

    public void setDefaultFile(String defaultFile) {
        this.defaultFile = defaultFile;
    }

}
