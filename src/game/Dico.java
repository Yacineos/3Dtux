/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author OUKKAL Yacine
 */
public class Dico {
    private ArrayList<String> listNiveau1 ;
    private ArrayList<String> listNiveau2 ;
    private ArrayList<String> listNiveau3 ;
    private ArrayList<String> listNiveau4 ;
    private ArrayList<String> listNiveau5 ;
    private String cheminFichierDico ;
    
    // le but du constructeur est d'initialiser les listes à partie du dictionnaire XML 
    public Dico(String cheminFichierDico){
        this.cheminFichierDico = cheminFichierDico ;
        this.listNiveau1 = new ArrayList<>();
        this.listNiveau2 = new ArrayList<>();
        this.listNiveau3 = new ArrayList<>();
        this.listNiveau4 = new ArrayList<>();
        this.listNiveau5 = new ArrayList<>();
    }
    
    public String getMotDepuisListNiveaux(int niveau){
        String res = "";
        switch(verifieNiveau(niveau)){
            case 1 : res = getMotDepuisListe(this.listNiveau1);
                break ;
            case 2 : res = getMotDepuisListe(this.listNiveau2);
                break;
            case 3 : res = getMotDepuisListe(this.listNiveau3);
                break;
            case 4 : res = getMotDepuisListe(this.listNiveau4);
                break;
            case 5 : res = getMotDepuisListe(this.listNiveau5);
                break;
        }
        return res;
    }
    
    public void ajoutMotADico(int niveau , String mot){
        switch(verifieNiveau(niveau)){
            case 1 : this.listNiveau1.add(mot);
                break ;
            case 2 : this.listNiveau2.add(mot);
                break;
            case 3 : this.listNiveau3.add(mot);
                break;
            case 4 : this.listNiveau4.add(mot);
                break;
            case 5 : this.listNiveau5.add(mot);
                break;
        }
    }
    
    public String getCheminFichierDico(){
        return "src/partie_XML/actes.xml";
    }
    //vérifie si le niveau est compris entre 1 et 5 
    // return le numéro entré en paramètre si vrai 
    // renvoie 1 sinon 
    private int verifieNiveau(int niveau){
        if(niveau > 0 && niveau < 6 ){
            return niveau ;
        }else{
            return 1 ;
        }
        
    }
    
    // pioche un mot au hazard de la liste donnée en paramètre 
    // si liste vide renovoie une exception 
    private String getMotDepuisListe(ArrayList<String> list){
        if(!list.isEmpty()){
            return list.get((int) ((Math.random()*1000)%list.size()));  
        }else{
            return "";
        }
    }
    
    private void afficheDictionnaireSuivantNiveau(int niveau ){
        switch(verifieNiveau(niveau)){
            case 1 : 
                System.out.println("Dictionnaire niveau 1 :");
                for(int i = 0 ; i < this.listNiveau1.size(); i++){
                    System.out.println(" -> "+this.listNiveau1.get(i));    
                }
                
                break ;
            case 2 :
                System.out.println("Dictionnaire niveau 2 :");
                for(int i = 0 ; i < this.listNiveau2.size(); i++){
                    System.out.println(" -> "+this.listNiveau2.get(i));    
                }
                break;
            case 3 : 
                System.out.println("Dictionnaire niveau 3 :");
                for(int i = 0 ; i < this.listNiveau3.size(); i++){
                    System.out.println(" -> "+this.listNiveau3.get(i));    
                }
                break;
            case 4 :
                System.out.println("Dictionnaire niveau 4 :");
                for(int i = 0 ; i < this.listNiveau4.size(); i++){
                    System.out.println(" -> "+this.listNiveau4.get(i));    
                }
                break;
            case 5 :
                System.out.println("Dictionnaire niveau 5 :");
                for(int i = 0 ; i < this.listNiveau5.size(); i++){
                    System.out.println(" -> "+this.listNiveau5.get(i));    
                }
                break;
        }
    }
    
    //remplie les listes à partir du doc XML
    public void lireDictionnaireDOM(String path, String filename) throws ParserConfigurationException, SAXException, IOException{
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path+""+filename);
        //récupérer l'element difficultés avec attribut niveau à 1 donc le premier fils de dictionnaire
        
        
        //---------------    niveau 1 ------------------------------------------
        Element difficulteNiveau1 = (Element) doc.getElementsByTagName("difficulte").item(0);
        
        
        NodeList motDifficulteNiveau1 = difficulteNiveau1.getElementsByTagName("mot");
        int nombreDeMotDifficulteNiveau1 = motDifficulteNiveau1.getLength();
        // on boucle sur tous les mots de difficulté 1
        for(int i = 0 ; i < nombreDeMotDifficulteNiveau1 ; i++){
            // on récupère le i ème mot 
            String motIndicei = motDifficulteNiveau1.item(i).getTextContent();
            // on l'ajoute au dictionnaire de mot niveau 1
            this.ajoutMotADico(1, motIndicei);
        }
        
        //---------------    niveau 2 ------------------------------------------
        Element difficulteNiveau2 = (Element) doc.getElementsByTagName("difficulte").item(1);
        
        
        NodeList motDifficulteNiveau2 = difficulteNiveau2.getElementsByTagName("mot");
        int nombreDeMotDifficulteNiveau2 = motDifficulteNiveau2.getLength();
        // on boucle sur tous les mots de difficulté 1
        for(int i = 0 ; i < nombreDeMotDifficulteNiveau2 ; i++){
            // on récupère le i ème mot 
            String motIndicei = motDifficulteNiveau2.item(i).getTextContent();
            // on l'ajoute au dictionnaire de mot niveau 1
            this.ajoutMotADico(2, motIndicei);
        }
        
        
        //---------------    niveau 3 ------------------------------------------
        Element difficulteNiveau3 = (Element) doc.getElementsByTagName("difficulte").item(2);
        
        
        NodeList motDifficulteNiveau3 = difficulteNiveau3.getElementsByTagName("mot");
        int nombreDeMotDifficulteNiveau3 = motDifficulteNiveau3.getLength();
        // on boucle sur tous les mots de difficulté 1
        for(int i = 0 ; i < nombreDeMotDifficulteNiveau3 ; i++){
            // on récupère le i ème mot 
            String motIndicei = motDifficulteNiveau3.item(i).getTextContent();
            // on l'ajoute au dictionnaire de mot niveau 1
            this.ajoutMotADico(3, motIndicei);
        }
        
        
        //---------------    niveau 4 ------------------------------------------
        Element difficulteNiveau4 = (Element) doc.getElementsByTagName("difficulte").item(3);
        
        
        NodeList motDifficulteNiveau4 = difficulteNiveau4.getElementsByTagName("mot");
        int nombreDeMotDifficulteNiveau4 = motDifficulteNiveau4.getLength();
        // on boucle sur tous les mots de difficulté 1
        for(int i = 0 ; i < nombreDeMotDifficulteNiveau4 ; i++){
            // on récupère le i ème mot 
            String motIndicei = motDifficulteNiveau4.item(i).getTextContent();
            // on l'ajoute au dictionnaire de mot niveau 1
            this.ajoutMotADico(4, motIndicei);
        }
        
        
        //---------------    niveau 5 ------------------------------------------
        Element difficulteNiveau5 = (Element) doc.getElementsByTagName("difficulte").item(4);
        
        
        NodeList motDifficulteNiveau5 = difficulteNiveau5.getElementsByTagName("mot");
        int nombreDeMotDifficulteNiveau5 = motDifficulteNiveau5.getLength();
        // on boucle sur tous les mots de difficulté 1
        for(int i = 0 ; i < nombreDeMotDifficulteNiveau5 ; i++){
            // on récupère le i ème mot 
            String motIndicei = motDifficulteNiveau5.item(i).getTextContent();
            // on l'ajoute au dictionnaire de mot niveau 1
            this.ajoutMotADico(5, motIndicei);
        }
        
        
    } 
        
    public static void main(String[] args ) throws ParserConfigurationException, SAXException, IOException{
        ArrayList<String> listNiveau1 = new ArrayList<>();
        ArrayList<String> listNiveau2 = new ArrayList<>();
        ArrayList<String> listNiveau3 = new ArrayList<>();
        ArrayList<String> listNiveau4 = new ArrayList<>();
        ArrayList<String> listNiveau5 = new ArrayList<>();
        
        Dico dic = new Dico("");
        /*
        dic.listNiveau1.add("Salam");
        dic.listNiveau1.add("Salam alikoum");
        dic.listNiveau2.add("Salam mes collègues");
        dic.listNiveau2.add("collègues");
        dic.listNiveau3.add("Salam bonjour");
        dic.listNiveau3.add("bonjour");
        dic.listNiveau4.add("Salam cv ");
        dic.listNiveau4.add("Salam cv ");
        dic.listNiveau5.add("Salam cv mes colllègues");
        dic.listNiveau5.add("Salam cv wowowo");
       */
        dic.lireDictionnaireDOM("src/partie_XML/", "dico.xml");
        
        dic.afficheDictionnaireSuivantNiveau(1);
        dic.afficheDictionnaireSuivantNiveau(2);
        dic.afficheDictionnaireSuivantNiveau(3);
        dic.afficheDictionnaireSuivantNiveau(4);
        dic.afficheDictionnaireSuivantNiveau(5);
        
        
        
        
       
        
    }
}
