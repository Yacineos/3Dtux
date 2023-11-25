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
    private int limiteTempsEnSecondes;
    
    public Partie(String date , String mot , int niveau ){
        this.date = date;
        this.mot = mot ;
        this.niveau = niveau ;
        this.limiteTempsEnSecondes = calculTempsAPartirDuNiveau(niveau);
    }
    public Partie(){
        
    }
    
    // et le pourcentage trouvé : int (arrondi) de lettres trouvées.
    public void setTrouve(int nbLettresRestantes){
        int nbLettreTrouve = this.mot.length() - nbLettresRestantes;
        this.trouve = (int) (nbLettreTrouve / this.mot.length());
    }
    public void setTemps(int temps){
        this.temps = temps ;
    }
    public int getNiveau(){
        return this.niveau;
    }

    public int getLimiteTempsEnSecondes() {
        return limiteTempsEnSecondes;
    }
    
    
    public String getMot(){
        return this.mot;
    }
    @Override
    public String toString(){
        return "date :"+this.date+", mot :"+this.mot+", niveau: "+this.niveau+", trouve :"+this.trouve+", temps :"+this.temps;
    }

    private int calculTempsAPartirDuNiveau(int niveau) {
        int res = 0;
        switch(getNiveau()){
                case 1: 
                    //on initialise le chrono à 5 minutes
                    res =300;
                    break;
                case 2:
                case 3: 
                    // on initialise le chrono à 3 minutes 
                    res = 180;
                    break;
                case 4:
                case 5: 
                    // on initialise le chrono à 2 minutes
                    res = 120;
                    break;
                default:
                    break;
            }
        return res ;
    }
    
}
