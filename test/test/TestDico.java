/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import game.Dico;
import java.util.ArrayList;

/**
 *
 * @author OUKKAL Yacine
 */
public class TestDico {

    public static void main(String[] args ){
        ArrayList<String> listNiveau1 = new ArrayList<>();
        ArrayList<String> listNiveau2 = new ArrayList<>();
        ArrayList<String> listNiveau3 = new ArrayList<>();
        ArrayList<String> listNiveau4 = new ArrayList<>();
        ArrayList<String> listNiveau5 = new ArrayList<>();
        
        Dico dic = new Dico("");
        
        
        dic.ajoutMotADico(1, "mot");
        dic.ajoutMotADico(1, "mot2");
        
        
        dic.ajoutMotADico(2, "wowowowo");
        dic.ajoutMotADico(2, "mot3");
        
        
        dic.ajoutMotADico(3, "mot4");
        dic.ajoutMotADico(3, "mot44");
        
        
        dic.ajoutMotADico(4, "mot654");
        dic.ajoutMotADico(4, "mot12345");
        dic.ajoutMotADico(4, "moldjhsflksdhft");
        
        
        
        dic.ajoutMotADico(5, "motgdfgfdg");
        dic.ajoutMotADico(5, "mot5555");
        dic.ajoutMotADico(5, "mot5555555555");
        
       
        System.out.println(""+dic.getMotDepuisListNiveaux(5));
        
        
        
       
        

}

}
