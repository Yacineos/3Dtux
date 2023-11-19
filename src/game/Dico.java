/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;

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
    }
    
    public String getMotDepuisListNiveaux(int niveau){
        
        return " " ;
    }
    
    public void ajoutMotADico(int niveau , String mot){
        
    }
    
    public String getCheminFichierDico(){
        return "";
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
    
}
