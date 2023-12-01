        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author oukkaly
 */

// définie une partie qui sera ensuite enregistré dans la DB légère XML des parties 
public class Partie {
    private final int tempsNiveau1 = 100;
    private final int tempsNiveau2 = 100;
    private final int tempsNiveau3 = 50;
    private final int tempsNiveau4 = 50;
    private final int tempsNiveau5 = 10;
    private String date ;
    private String mot ;
    private int niveau ;
    private int trouve;
    private int temps ;
    private int limiteTempsEnSecondes;
    
    
    //construction d'une nouvelle partie 
    public Partie(String date , String mot , int niveau ){
        this.date = date;
        this.mot = mot ;
        this.niveau = niveau ;
        this.limiteTempsEnSecondes = calculTempsAPartirDuNiveau(niveau);
    }
    public Partie(){
        
    }
    // construction d'une partie à partir d'un element DOM correspondant à une partie d'un document XML
    public Partie(Element partieElt){
        //On recupere les elemnts néceissaires et on extrait la valeure
        Element motElt =(Element) partieElt.getElementsByTagName("mot").item(0);
        Element tempsElt = (Element) partieElt.getElementsByTagName("temps").item(0);
        Element niveauElt = (Element) partieElt.getElementsByTagName("niveau").item(0);
        
        String mot=motElt.getTextContent();
        String temps=tempsElt.getTextContent();
        String date=partieElt.getAttribute("date");
        String trouvé=partieElt.getAttribute("trouvé");
        String niveauString=motElt.getAttributeNode("niveau").getTextContent();
        
        
        
        this.mot=mot;
        this.temps=(int) Double.parseDouble(temps);
        this.date=Profil.xmlDateToProfileDate(date);
        this.niveau=Integer.parseInt(niveauString);
        this.limiteTempsEnSecondes = calculTempsAPartirDuNiveau(niveau);
         try{
            this.trouve=Integer.parseInt(trouvé);
        }catch(Exception ex){//Si trouvé n'est pas initialisé on attrape l'exception et on initialise a 0
            this.trouve=0;
        }
        
    }
    
    // crée le bloc XML représentant une partie à partir du param Doc 
    public Element getPartie(Document doc){
        //Element partie
        Element partieElt = doc.createElement("partie");
        
        //System.out.println("la date est : "+this.date);
        //attribut date
        String date=this.date;
        partieElt.setAttribute("date",date);
        
        //attribut trouvé 
        partieElt.setAttribute("trouvé", String.valueOf(this.trouve));

        //element temps 
        Element tempsElt = doc.createElement("temps");
        Text tempsTxt = doc.createTextNode(String.valueOf(this.temps));
        tempsElt.appendChild(tempsTxt);
        
        //element mot et attribut niveau
        Element motElt = doc.createElement("mot");
        Text motTxt= doc.createTextNode(String.valueOf(this.mot));
        motElt.setAttribute("niveau", String.valueOf(this.niveau));
        motElt.appendChild(motTxt);
        
        //on ajoute les element au doc
        partieElt.appendChild(tempsElt);
        partieElt.appendChild(motElt);
        
        return partieElt;
        
    }
    
    // et le pourcentage trouvé : int (arrondi) de lettres trouvées.
    public void setTrouve(int nbLettresRestantes){
        int nbLettreTrouve = this.mot.length() - nbLettresRestantes;
        this.trouve = (int) (nbLettreTrouve / this.mot.length())*100;
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

    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date ;
    }
    public int getTemps(){
        return this.temps;
    }
     
    private int calculTempsAPartirDuNiveau(int niveau) {
        int res = 0;
        switch(getNiveau()){
                case 1: 
                    //on initialise le chrono à 5 minutes
                    res =tempsNiveau1;
                    break;
                case 2:
                case 3: 
                    // on initialise le chrono à 3 minutes 
                    res = tempsNiveau3;
                    break;
                case 4:
                case 5: 
                    // on initialise le chrono à 2 minutes
                    res = tempsNiveau5;
                    break;
                default:
                    break;
            }
        return res ;
    }
    
}
