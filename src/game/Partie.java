/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author oukkaly
 */
public class Partie {
    private String date ;
    private String mot ;
    private int niveau ;
    private int trouve;
    private int temps ;
    
    public Partie(String date , String mot , int niveau ){
        
    }
    public Partie(){
        
    }
    
    public void setTrouve(int nbLettresRestantes){
        
    }
    public void setTemps(int temps){
        
    }
    public int getNiveau(){
        return 0;
    }
    
    @Override
    public String toString(){
        return "date :"+this.date+", mot :"+this.mot+", niveau: "+this.niveau+", trouve :"+this.trouve+", temps :"+this.temps;
    }
    
}
