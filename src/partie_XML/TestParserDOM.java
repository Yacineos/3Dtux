/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partie_XML;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;

/**
 *
 * @author OUKKAL Yacine
 */
public class TestParserDOM {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        
        Document doc = builder.parse("src/partie_XML/actes.xml");
        
        System.out.println(""+doc.getElementsByTagName("acte").item(0).getAttributes().item(3));
    }
}
