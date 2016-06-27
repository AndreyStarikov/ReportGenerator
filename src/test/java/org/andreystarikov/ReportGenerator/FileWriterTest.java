package org.andreystarikov.ReportGenerator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileWriterTest {

    @Test
    public void testSaveFile() throws Exception {
        FileWriter fl = new FileWriter();
        fl.saveFile("тестик" ,"src\\test\\resources\\fileWriterTest.txt");
    }
    @Test
    public void testSaveFile2() throws Exception {
        FileWriter fl = new FileWriter();
        ArrayList<String> strList = new ArrayList<>();
        strList.add("Номер");
        strList.add("Дата");
        strList.add("Имя");

        fl.saveFile(strList ,"src\\test\\resources\\fileWriterTest2.txt");
    }
}