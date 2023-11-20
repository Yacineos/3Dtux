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
    
    
    public static void main(String[] args ){
        ArrayList<String> listNiveau1 = new ArrayList<>();
        ArrayList<String> listNiveau2 = new ArrayList<>();
        ArrayList<String> listNiveau3 = new ArrayList<>();
        ArrayList<String> listNiveau4 = new ArrayList<>();
        ArrayList<String> listNiveau5 = new ArrayList<>();
        
        Dico dic = new Dico("");
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
       
        System.out.println(""+dic.getMotDepuisListNiveaux(6));
        
        
        
       
        
    }
}
