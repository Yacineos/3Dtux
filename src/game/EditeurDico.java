package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.io.File;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.util.logging.Level;
import java.util.logging.Logger;

    /**
     *
     * @author OUKKAL Yacine
     */

// class qui permet à l'aide d'un parseur DOM de modifier le dictionnaire , c'est à dire ajouter des nouveau mots 
public class EditeurDico {
    public Document _doc;
    public String filename;

    public EditeurDico(String filename){
        this.filename=filename;
    }

    public void editer(String mot, int niveau){
            lireDOM(filename);
            ajouterMot(mot, niveau);
            ecrireDOM(filename);
    }
    // on suppose que le dictionnaire est au moins remplie avec un mot de chaque difficultee
    public void ajouterMot(String mot, int niveau){
        try{
        
          //on creer un element mot
        Element motElt=_doc.createElement("mot");

        //text du mot
        Text motTxt = _doc.createTextNode(mot);
        motElt.appendChild(motTxt);
        
        // on récupère l'element selon la difficulté
        switch(niveau){
            case 1 :
                Element elementDifficulte1=(Element)_doc.getElementsByTagName("difficulte").item(0);
                elementDifficulte1.appendChild(motElt);
                break;
            case 2 :
                Element elementDifficulte2=(Element)_doc.getElementsByTagName("difficulte").item(1);
                elementDifficulte2.appendChild(motElt);
                break;
            case 3 :
                Element elementDifficulte3=(Element)_doc.getElementsByTagName("difficulte").item(2);
                elementDifficulte3.appendChild(motElt);
                break;
            case 4 :
                Element elementDifficulte4=(Element)_doc.getElementsByTagName("difficulte").item(3);
                elementDifficulte4.appendChild(motElt);
                break;
            case 5 :
                Element elementDifficulte5=(Element)_doc.getElementsByTagName("difficulte").item(4);
                elementDifficulte5.appendChild(motElt);
                break;
        }
        }catch(Exception e){
            System.out.println(""+e);
            e.printStackTrace();
        }
      
        
    }

    //charge le fichier xml dans la mémoire 
    public void lireDOM(String filename) {
 
        _doc = fromXML(filename);


    }

    // sauvegarder le fichier chargé en mémoire dans le fichier xml
    public void ecrireDOM(String filename){
        toXML(filename);
    }
    
    
    public Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            System.out.println("DocumentNotFOund "+ex);
            return null;
        }
         
    }

    public void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(_doc, nomFichier);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
   
    
    
    public static void main(String[] args){
        EditeurDico editDic = new EditeurDico("src/partie_XML/dico.xml");
        
       
    }
   
   
}