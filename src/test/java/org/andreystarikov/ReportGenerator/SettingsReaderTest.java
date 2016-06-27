package org.andreystarikov.ReportGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettingsReaderTest {

    private SettingsReader settingsReader;

    @Before
    public void setup() {
        settingsReader = new SettingsReader();
    }

    @Test
    public void testGetPageHeight() throws Exception {
        settingsReader.setSettings("settings.xml");
        int height = settingsReader.getPage().getHeight();
        assertEquals("Не совпало", 12, height);
    }

    @Test
    public void testGetPageWidth() throws Exception {
        settingsReader.setSettings("settings.xml");
        int width = settingsReader.getPage().getWidth();
        assertEquals("Не совпало", 32, width);
    }

    @Test
    public void testGetPageWidthFromTestFile() throws Exception {
        settingsReader.setSettings("src\\test\\resources\\testSettings.xml");
        int width = settingsReader.getPage().getWidth();
        assertEquals("Не совпало", 30, width);
    }

    @Test
    public void testGetColumns() throws Exception {
        settingsReader.setSettings("src\\test\\resources\\testSettings.xml");
        ArrayList<Column> columnsList= settingsReader.getColumnsList();
        int width = columnsList.get(0).getWidth();
        assertEquals("Не совпало", 20, width);
    }

    @Test
    public void testGetColumnsTitle0() throws Exception {
        settingsReader.setSettings("settings.xml");
        ArrayList<Column> columnsList= settingsReader.getColumnsList();
        String title = columnsList.get(0).getTitle();
        assertEquals("Не совпало", "Номер", title);
    }
    @Test
    public void testGetColumnsTitle1() throws Exception {
        settingsReader.setSettings("settings.xml");
        ArrayList<Column> columnsList= settingsReader.getColumnsList();
        String title = columnsList.get(1).getTitle();
        assertEquals("Не совпало", "Дата", title);
    }
}