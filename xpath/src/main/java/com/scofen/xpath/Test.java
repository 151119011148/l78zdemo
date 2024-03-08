package com.scofen.xpath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/11/17 8:31 PM
 **/
public class Test {

    public static void main(String[] args) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("/Users/gaoying/Desktop/work/l78zdemo/xpath/src/main/resources/test.xml"));
            Element root = document.getRootElement();
            Node book1 = root.selectSingleNode("//book1");
            Node aggregate = book1.selectSingleNode("//aggregate");
            aggregate.selectNodes("./value").stream()
                    .map(node -> ((Node)node).getText())
                    .collect(Collectors.toList());
            List<String> values = (List<String>) book1.selectSingleNode("//aggregate")
                    .selectNodes("//value")
                    .stream()
                    .map(node -> ((Node)node).getText())
                    .collect(Collectors.toList());
            List<Node> book2 = book1.selectNodes("following-sibling::*");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
