package org.andreystarikov.ReportGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnFormatterTest {

    private ColumnFormatter cf;

    @Before
    public void setup() {
        cf = new ColumnFormatter();
    }

    @Test
    public void testFormat() {
        ArrayList<String> result = cf.format("Юлианна-Оксана Сухово-Кобылина", 7);
        for (String s : result) {
            System.out.println(s);
        }
    }
}