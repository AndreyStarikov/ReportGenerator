package org.andreystarikov.ReportGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TableMakerTest {

    private SettingsReader settingsReader;
    private TableMaker tableMaker;

    @Before
    public void setup() {
        settingsReader = new SettingsReader();
    }

    @Test
    public void testMakeDelimiter() throws Exception {
        tableMaker = new TableMaker(settingsReader);
        String result = tableMaker.makeDelimiter(7);
        assertEquals("Ошибка", "-------\n", result);
    }

    @Test
    public void testStringBuilder() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("Номер", 5));
        columnsList.add(new Column("Дата", 5));
        columnsList.add(new Column("Имя", 4));
        settingsReader.setColumnsList(columnsList);
        tableMaker = new TableMaker(settingsReader);

        String[] strArr = {null, "12/05", "Петр"};
        String result = tableMaker.stringBuilder(strArr);
        assertEquals("Ошибка", "|       | 12/05 | Петр |\n", result);
    }

    @Test
    public void testStringBuilder2() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("Номер", 5));
        columnsList.add(new Column("Дата", 5));
        columnsList.add(new Column("Имя", 4));
        settingsReader.setColumnsList(columnsList);
        tableMaker = new TableMaker(settingsReader);

        String[] strArr = {null, null, "Петр"};
        String result = tableMaker.stringBuilder(strArr);
        assertEquals("Ошибка", "|       |       | Петр |\n", result);
    }

    @Test
    public void testMakeHeader() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("Номер", 5));
        columnsList.add(new Column("Дата", 5));
        columnsList.add(new Column("Имя", 4));
        settingsReader.setColumnsList(columnsList);
        tableMaker = new TableMaker(settingsReader);

        String result = tableMaker.makeHeader();
        assertEquals("Ошибка", "| Номер | Дата  | Имя  |\n", result);
    }

    @Test
    public void testMakeHeader2() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("Номер", 4));
        columnsList.add(new Column("Дата", 5));
        columnsList.add(new Column("Имя", 4));
        settingsReader.setColumnsList(columnsList);
        tableMaker = new TableMaker(settingsReader);

        String result = tableMaker.makeHeader();
        assertEquals("Ошибка", "| Номе | Дата  | Имя  |\n", result);
    }

    @Test
    public void testMakeHeader3() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("", 4));
        columnsList.add(new Column("Дата", 5));
        columnsList.add(new Column("Имя", 4));
        settingsReader.setColumnsList(columnsList);
        tableMaker = new TableMaker(settingsReader);

        String result = tableMaker.makeHeader();
        assertEquals("Ошибка", "|      | Дата  | Имя  |\n", result);
    }

    @Test
    public void testTableEditor() throws Exception {
        ArrayList<Column> columnsList = new ArrayList<>();
        columnsList.add(new Column("Номер", 8));
        columnsList.add(new Column("Дата", 7));
        columnsList.add(new Column("ФИО", 7));
        settingsReader.setColumnsList(columnsList);
        settingsReader.setPage(new Page(32, 12));
        tableMaker = new TableMaker(settingsReader);

        ArrayList<String[]> inputList = new ArrayList<>();
        String[] arr1 = {"4", "28/11", "Ким Чен Ир"};
        inputList.add(arr1);
        inputList.add(new String[]{"5", "29/11/2009", "Юлианна-Оксана Сухово-Кобылина"});
        ArrayList<String> result = tableMaker.tableEditor(inputList);
        for (String s : result) {
            System.out.print(s);
        }
    }
}