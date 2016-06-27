package org.andreystarikov.ReportGenerator;

import java.util.ArrayList;

public class ColumnFormatter {

    /**
     * Разбивает одну входящую строку на список коротких, длинной, равной входящему параметру.
     *
     * @param input входящая строка
     * @param limit определяет длину строки в списке
     * @return список строк длинной в ширину колонки
     */
    public ArrayList<String> format(String input, int limit) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (input.length() > limit) {
            char[] chArr = input.toCharArray();
            ArrayList<Integer> delimiter = makeDelimiterList(chArr);

            int nextpos = 0;
            int pos = 0;
            int begin = 0;
            int end = 0;
            int i = 0;

            if (delimiter != null) {
                nextpos = delimiter.get(i);
            } else {
                pos = nextpos;
                nextpos = chArr.length + 1;
            }
            while (end < chArr.length) {
                if (nextpos <= begin + limit && nextpos <= chArr.length) {
                    if (delimiter != null) {
                        if (delimiter.size() > i) {
                            pos = nextpos;
                            nextpos = delimiter.get(i);
                            i++;
                        } else {
                            pos = nextpos;
                            nextpos = chArr.length + 1;
                        }
                    }
                } else {
                    if (Character.isWhitespace(chArr[begin]) || Character.isSpaceChar(chArr[begin])) {
                        end++;
                        begin++;
                    }
                    if (pos == begin + limit) {
                        end += limit;
                    } else {
                        if (pos > end) {
                            end = pos + 1;
                        } else {
                            if (end + limit > chArr.length) {
                                end = chArr.length;
                            } else {
                                end += limit;
                            }
                        }
                    }
                    char[] ch2 = new char[end - begin];
                    System.arraycopy(chArr, begin, ch2, 0, end - begin);
                    String a;
                    if (end - begin < limit) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(ch2);
                        for (int k = end - begin; k < limit; k++) {
                            sb.append(" ");
                        }
                        a = sb.toString();
                    } else {
                        a = charArrayToString(ch2);
                    }
                    arrayList.add(a);
                    begin = end;
                }
            }
        } else {
            if (input.length() == limit) {
                arrayList.add(input);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(input);
                for (int i = input.length(); i < limit; i++) {
                    sb.append(" ");
                }
                arrayList.add(sb.toString());
            }
        }
        return arrayList;
    }

    /**
     * Возвращает список индексов разделителей во входящем массиве.
     * Разделителями являются любые символы кроме цифр и букв.
     *
     * @param chArr массив символов
     * @return список индексов разделителей
     */
    private ArrayList<Integer> makeDelimiterList(char[] chArr) {
        ArrayList<Integer> delimiter = null;
        for (int i = 0; i < chArr.length; i++) {
            if (!Character.isLetterOrDigit(chArr[i])) {
                if (delimiter == null) {
                    delimiter = new ArrayList<>();
                }
                delimiter.add(i);
            }
        }
        return delimiter;
    }

    public String charArrayToString(char[] chArr) {
        return String.valueOf(chArr);
    }
}
