package org.andreystarikov.ReportGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class SettingsReader {


    private ArrayList<Column> columnsList = null;
    private Page page;

    public void setSettings(String fileName) {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            setColumns(doc);
            setPage(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setColumns(Document doc) {
        NodeList columnNodeList = doc.getElementsByTagName("column");

        for (int s = 0; s < columnNodeList.getLength(); s++) {
            Node columnNode = columnNodeList.item(s);

            if (columnNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elementColumnNode = (Element) columnNode;

                Column column = new Column();
                column.setTitle(getTitle(elementColumnNode));
                column.setWidth(getSize(elementColumnNode, "width"));
                if (columnsList == null) {
                    columnsList = new ArrayList<>();
                }
                columnsList.add(column);
            }
        }
    }

    private String getTitle(Element elementColumnNode) {
        NodeList titleNodeList = elementColumnNode.getElementsByTagName("title");
        Element titleElement = (Element) titleNodeList.item(0);
        String title = ((Node) titleElement.getFirstChild()).getNodeValue();
        return title;
    }

    private int getSize(Element elementColumnNode, String tag) {
        NodeList sizeNodeList = elementColumnNode.getElementsByTagName(tag);
        Element sizeElement = (Element) sizeNodeList.item(0);
        int size = Integer.parseInt(((Node) sizeElement.getFirstChild()).getNodeValue());
        return size;
    }

    public ArrayList<Column> getColumnsList() {
        return columnsList;
    }

    public Page getPage() {
        return page;
    }

    public void setColumnsList(ArrayList<Column> columnsList) {
        this.columnsList = columnsList;
    }

    private void setPage(Document doc) {
        NodeList pageNodeList = doc.getElementsByTagName("page");
        Node pageNode = pageNodeList.item(0);
        if (pageNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elementColumnNode = (Element) pageNode;
            page = new Page();
            page.setHeight(getSize(elementColumnNode, "height"));
            page.setWidth(getSize(elementColumnNode, "width"));
        }
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
