/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.logging.Level;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.io.File;
import java.util.logging.Logger;

/**
 *
 * @author oukkaly
 */
public class Profil {
    private String nom ;
    private String avatar ; // chemin vers un fichier .jpg
    // can be changed to Date type 
    private String dateNaissance ;
    private ArrayList<Partie> parties ;
    public Document _doc;
    
    public Profil(String nom , String dateNaissance ){
        this.nom = nom ;
        this.dateNaissance = dateNaissance ;
        this.avatar ="";
        this.parties = new ArrayList<>();
        System.out.println("game.Profil.<init>() :"+dateNaissance);
        createXmlProfil();
    }
    
    // Cree un DOM à partir d'un fichier XML
    public Profil(String nomJoueur) throws Exception  {
        _doc = fromXML("src/partie_XML/profil/"+nomJoueur+".xml");
            // parsing à compléter
            // ?!#?!
             //racine
            Element profilElt = _doc.getDocumentElement();
            //recuperation des attributs
            String nom =profilElt.getElementsByTagName("nom").item(0).getTextContent();
            String avatar=profilElt.getElementsByTagName("avatar").item(0).getTextContent();
            String dateNaissanceXML=profilElt.getElementsByTagName("anniversaire").item(0).getTextContent();

            this.nom=nom;
            this.avatar=avatar;
            this.dateNaissance=xmlDateToProfileDate(dateNaissanceXML); //on convertie la date au bon format

            //debug
            System.out.println("Initialisation profil: nom="+nom+" avatar="+avatar+" date="+dateNaissance);


            parties= new ArrayList<Partie>();
            NodeList partieList = profilElt.getElementsByTagName("partie");
            for (int i = 0; i < partieList.getLength(); i++) { //on parcours la liste des parties
                Element partieElt =(Element) partieList.item(i); //on recupere l'element de la partie
                Partie partie = new Partie(partieElt);
                parties.add(partie);
                System.out.println("Partie initialisée: "+partie);
            }
        
    }
    
     /** 
     * Crée un nouveau Document et le cnstruit a partir des attribut de l'instance, puis sauvegarde 
     * le profil dans un nouveau ficheir xml
    */
    private void createXmlProfil(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            _doc = db.newDocument();
            
            //racine et espaces de nom
            Element profilElt = _doc.createElement("profil");
            profilElt.setAttribute("xmlns", "http://myGame/tux");
            profilElt.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            profilElt.setAttribute("xsi:schemaLocation", "http://myGame/tux xsd/profil.xsd");
            _doc.appendChild(profilElt);
            
            //nom
            Element nomElt=_doc.createElement("nom");
            Text nomTxt=_doc.createTextNode(nom);
            nomElt.appendChild(nomTxt);
            profilElt.appendChild(nomElt);
            
            //avatar
            Element avatarElt=_doc.createElement("avatar");
            Text avatarTxt=_doc.createTextNode(avatar);
            avatarElt.appendChild(avatarTxt);
            profilElt.appendChild(avatarElt);
            
            //date
            Element dateElt=_doc.createElement("anniversaire");
            Text dateTxt=_doc.createTextNode(profileDateToXmlDate(dateNaissance));
            dateElt.appendChild(dateTxt);
            profilElt.appendChild(dateElt);
            
            Element partiesElt=_doc.createElement("parties");
            profilElt.appendChild(partiesElt);

            // DocumentTransform.writeDoc(_doc, "Data/xml/profils/"+nom+".xml");
            sauvegarder("src/partie_XML/profil/"+nom+".xml");
            System.out.println("Nouveau profil créé!");
        }catch(Exception e){
            System.out.println("Erreur creation profil: "+e);
        }
    }
    public Profil(){
        
    }
    
   // Sauvegarde un DOM en XML
    public void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(_doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Cree un DOM à partir d'un fichier XML
    public Document fromXML(String nomFichier) throws Exception {
        
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        
    }
    
    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }
    
     /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    public static String profileDateToXmlDate(String profileDate) {
        String date;
       // System.out.println("game.Profil.profileDateToXmlDate()"+profileDate);
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }
    
    
    public void ajoueterPartie(Partie p){
        parties.add(p);
        Element partieElt=p.getPartie(_doc);
        Element partiesElt= (Element) _doc.getElementsByTagName("parties").item(0);
        partiesElt.appendChild(partieElt);
    }
    
    public int getDernierNiveau(){
        return 0 ;
    }
    
    public void sauvegarder(String nomJoueur){
        toXML("src/partie_XML/profil/"+nomJoueur+".xml");
    }
    
    
    public String getNom(){
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

 
    public ArrayList<Partie> getParties() {
        return parties;
    }
    
    
    
    
    
}
