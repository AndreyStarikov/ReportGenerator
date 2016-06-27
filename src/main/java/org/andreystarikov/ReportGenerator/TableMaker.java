package org.andreystarikov.ReportGenerator;

import java.util.ArrayList;


public class TableMaker {

    private SettingsReader settingsReader;
    private String header;
    private int pos;

    public TableMaker(SettingsReader settingsReader) {
        this.settingsReader = settingsReader;
    }

    /**
     * Возвращает таблицу в виде списка строк.
     *
     * @param inputList список массивов строк.
     * @return таблица в виде списка строк
     */
    public ArrayList<String> tableEditor(ArrayList<String[]> inputList) {
        ArrayList<String> editList = new ArrayList<>();
        pos = 0;
        for (int i = 0; i < inputList.size(); i++) {
            ArrayList<String[]> transientList = new ArrayList<>();//список массивов строк, в который превращается один элемент списка inputList
            String[] inputString = inputList.get(i);
            for (int j = 0; j < inputString.length; j++) {
                ColumnFormatter columnFormatter = new ColumnFormatter();
                ArrayList<String> arrayList = columnFormatter.format(inputString[j], settingsReader.getColumnsList().get(j).getWidth());//одна колонка из списка transientList
                transientList = makeTransientList(transientList, arrayList, j, inputString.length);
            }
            editList = makeEditList(editList, transientList);
        }
        return editList;
    }

    /**
     * Добавляет transientList в editList с помощью метода add, но только в уже отформатированном виде в виде строки
     * +заголовки + знаки разделения страницы + отбивки
     *
     * @param editList      список
     * @param transientList список
     * @return editList
     */
    private ArrayList<String> makeEditList(ArrayList<String> editList,
                                           ArrayList<String[]> transientList) {
        int transientListSize = transientList.size();
        int pageWidth = settingsReader.getPage().getWidth();
        int pageHeight = settingsReader.getPage().getHeight();
        if (editList.isEmpty()) {
            editList.add(header = makeHeader());
            pos++;
        }
        //Влезет ли transientList в оставшееся место на странице?
        if (pageHeight - pos >= transientListSize + 1) {
            editList.add(makeDelimiter(pageWidth));
            for (String[] aTransientList : transientList) {
                editList.add(stringBuilder(aTransientList));
            }
            pos = pos + transientListSize + 1;
        } else {
            //Влезет ли transientList целиком на страницу?
            if (pageHeight - 2 >= transientListSize) {
                editList.add(nextPage());
                editList.add(header);
                editList.add(makeDelimiter(pageWidth));
                for (String[] aTransientList : transientList) {
                    editList.add(stringBuilder(aTransientList));
                }
                pos = transientListSize + 2;
            } else {
                for (int i = 0; i < transientListSize; i = i + pageHeight - 2) {
                    editList.add(nextPage());
                    editList.add(header);
                    editList.add(makeDelimiter(pageWidth));
                    for (int j = i; j < pageHeight - 2 + i && j < transientListSize; j++) {
                        editList.add(stringBuilder(transientList.get(j)));
                    }
                    pos = transientListSize - i + 2;
                }
            }
        }
        return editList;
    }

    /**
     * Формирует строку на основе массива
     *
     * @param strings массив строк
     * @return отформатированную строку
     */
    public String stringBuilder(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (!(strings[i] == null)) {
                String s = String.format("| %s ", strings[i]);
                sb.append(s);
            } else {
                StringBuilder innersb = new StringBuilder();
                int width = settingsReader.getColumnsList().get(i).getWidth();
                for (int j = 0; j < width; j++) {
                    innersb.append(" ");
                }
                String s = String.format("| %s ", innersb.toString());
                sb.append(s);
            }
        }
        sb.append("|\n");
        return sb.toString();
    }

    /**
     * Возвращает строку-разделитель
     *
     * @param pageWidth длина строки разделителя
     * @return строка разделитель
     */
    public String makeDelimiter(int pageWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pageWidth; i++) {
            sb.append("-");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Возвращает заголовок
     *
     * @return строка-заголовок
     */
    public String makeHeader() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < settingsReader.getColumnsList().size(); i++) {
            int width = settingsReader.getColumnsList().get(i).getWidth();
            String title = settingsReader.getColumnsList().get(i).getTitle();
            int titleLength = title.length();
            StringBuilder innersb;
            if (titleLength == width) {
                sb.append(String.format("| %s ", title));
            } else {
                innersb = new StringBuilder();
                if (titleLength > width) {
                    for (int j = 0; j < width; j++) {
                        innersb.append(title.charAt(j));
                    }
                    sb.append(String.format("| %s ", innersb.toString()));
                } else {
                    for (int j = 0; j < titleLength; j++) {
                        innersb.append(title.charAt(j));
                    }
                    for (int j = titleLength; j < width; j++) {
                        innersb.append(" ");
                    }
                    sb.append(String.format("| %s ", innersb.toString()));
                }
            }
        }
        sb.append("|\n");
        return sb.toString();
    }

    public String nextPage() {
        return "~\n";
    }

    /**
     * Добавляет в transientList список arrayList как колонку
     *
     * @param transientList исходный список, в который добавляется список arrayList
     * @param arrayList     список строк
     * @param column        номер колонки
     * @param length        длина массива, который является элементом возвращаемого списка.
     * @return transientList
     */
    private ArrayList<String[]> makeTransientList(ArrayList<String[]> transientList,
                                                  ArrayList<String> arrayList, int column, int length) {
        for (int i = 0; i < arrayList.size(); i++) {
            String[] stringArray;
            if (transientList.size() == i) {
                stringArray = new String[length];
                stringArray[column] = arrayList.get(i);
                transientList.add(stringArray);
            } else {
                stringArray = transientList.get(i);
                stringArray[column] = arrayList.get(i);
                transientList.set(i, stringArray);
            }
        }
        return transientList;
    }

    public void setSettingsReader(SettingsReader settingsReader) {
        this.settingsReader = settingsReader;
    }

}
