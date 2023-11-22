/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partie_XML;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author OUKKAL Yacine
 */
public class TestParserSAX implements ContentHandler {
    private int numberElement ; // on compte le nombre d'éléments
    private int numberWhiteSpace; // on compte le nombre de zone text vide 
    
    
    // intialise les attributs à 0 
    @Override
    public void startDocument(){
        this.numberElement = 0 ;
        this.numberWhiteSpace = 0 ;
    }

    // affiche les valeurs des attributs si la fin du document est atteint
    @Override
    public void endDocument() throws SAXException {
        System.out.println(this.numberElement);
        System.out.println(this.numberWhiteSpace);
        
    }
    
     // il trouve un element donc on increment numberElement
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.numberElement++;
    }
    
    
    //on trouve un white space donc on increment cet attribut
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        this.numberWhiteSpace++;
    }
    
    
    public static void main(String[] args) throws SAXException, IOException{
        XMLReader parser ;
        parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(new TestParserSAX());
        parser.parse("src/partie_XML/testSAX.xml");    
    }
    
    
    

    @Override
    public void setDocumentLocator(Locator locator) {
    }
    
    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }

   

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
    }
    
    
    
    
    
  
}
