/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author oukkaly
 */
public abstract class Jeu {
    
    protected Env env ;
    private Room room ; 
    private Profil profil;
    private ArrayList<Letter> lettres;
    private ArrayList<Position> listPositionLettres ;
    private Dico dico; 
    private Tux tux;
    
    public Jeu() throws ParserConfigurationException, SAXException, IOException{ 
        // Crée un nouvel environnement
       
        env = new Env();

        // Instancie une Room
        room = new Room();
        
        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);
        
        // Instancie un profil par défaut
        profil = new Profil();
        dico = new Dico("");
        dico.lireDictionnaireDOM("src/partie_XML/","dico.xml" );

        String mot = dico.getMotDepuisListNiveaux(1);
        System.out.println(""+mot);
        
        listPositionLettres = new ArrayList<>();
        lettres = new ArrayList<>();
        
        
        fillLettersPositionList(mot.length());
        
        
        for(int i = 0 ; i < mot.length(); i++){
            Letter l = new Letter(mot.charAt(i), listPositionLettres.get(i).getX(), listPositionLettres.get(i).getY(), env, room);
            lettres.add(l);
        }
        
        
        printList(listPositionLettres);
        
      
        
    }
    public void execute() {
 
        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        joue(new Partie());
         
        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
    }
    
    public void joue(Partie partie) {
 
        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);
 
        // Instancie un Tux
        this.tux = new Tux(env , room);
        env.addObject(tux);
        
        // instancie une lettre pour la tester 
        
        /*
        env.addObject(letterN);
        env.addObject(letterI);
        env.addObject(letterA);
        env.addObject(letterSpace);
        */
        // ou bien 
        Arrays.asList(this.lettres.get(0), this.lettres.get(1), this.lettres.get(2), this.lettres.get(3)).forEach(env::addObject);
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        demarrepartie(partie);
         
        // Boucle de jeu
        Boolean finished;
        finished = false;
        

        
        while (!finished) {
            
            env.setCameraXYZ(this.tux.getX(), 60, this.tux.getZ()+100);
 
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
 
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            // ... (sera complété plus tard) ...
               tux.deplace();
               
               //System.out.println(collision(this.lettres.get(0)));
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
 
    }    
    
    protected abstract void demarrepartie(Partie partie);
    
    protected abstract void appliqueRegles(Partie partie);
    
    protected abstract void terminePartie(Partie partie);

    public ArrayList<Letter> getLettres() {
        return lettres;
    }
    
    
    
    //la méthode distance(letter:Letter) : double de la classe Jeu renvoie la distance du personnage tux à la lettre.
    protected double distance(Letter letter){
        //TODO 1.1
        // sachant que le point (0,0) c'est le point le plus éloigné à gauche
        
        // on récupère la position du tux
        // axe des gauche droite
         // axe des profondeur
        
        // on récupère la postion de la lettre 
        /*
                System.out.println("tuxX:"+this.tux.getX()); 
        System.out.println("letterN:"+letter.getX()); 
        System.out.println("tuxZ:"+this.tux.getZ()); 
        System.out.println("letterZ:"+letter.getZ()); 
        */
        // formule calcule de distance entre 2 points 
        // d = racineCarre ( puissance2 ( x2 - x1 ) + puissance2 ( y2 - y1 ) )
        double dist = Math.sqrt(Math.pow(( this.tux.getX() - letter.getX()),2) + Math.pow(this.tux.getZ()-letter.getZ(), 2));
        return dist;
    }
    //la méthode collision(letter:Letter) : Boolean de la classe Jeu renvoie true si le personnage et la lettre sont en collision. Attention à la taille (scale) de vos objets.
    protected boolean collision(Letter letter){
        //TODO 1.2
        //System.out.println(""+distance(letter));
        return distance(letter) < 10.0 ;
    }
    
    
    
    protected void fillLettersPositionList(int nbLetters){
        
        // on va créer des position random pour toutes les lettres + l'objet tux 
        for(int i = 0 ; i < nbLetters+1 ; i++){
            boolean done = false ;
            //on génère une position random
            Position p = generateRandomCoor(this.room.getWidth() , this.room.getDepth()) ;
            //System.out.println("on est pas entré dans la boucle "+ p.toString());
            // TantQue la position générée est dans la liste on en génère une autre 
            while(!done){
                //System.out.println("on est entré dans la boucle "+ p.toString());
                if(distanceEntreLeResteDesLettres(p)){
                    //System.out.println("est ce que la position :"+p.toString()+" est dans la liste :"+listPositionLettres.contains(p));
                    this.listPositionLettres.add(p);
                    done = true ;
                }else{
                    p = generateRandomCoor(this.room.getWidth() , this.room.getDepth()) ;
                    //System.out.println("ohhh je genere une nouvelle :"+p.toString()+" est dans la liste ");

                }
            }
        }
        
    }
    private boolean distanceEntreLeResteDesLettres(Position p){
        boolean estEnCollisionAvecUneAutreLettre = false ;
        int i = 0 ;
        while(i < listPositionLettres.size() && !listPositionLettres.get(i).samePosition(p)){
            i++;
        }
        return  !(i < listPositionLettres.size()) ;
    }
    // generates a random coordonates that are between (0 and maxX ) && ( 0 and maxY)
    protected Position generateRandomCoor(int maxX , int maxY){
        Position p = new Position();
        p.setY((Math.random()*1000) % (maxY-20));
        p.setX((Math.random()*1000) % (maxX-20));
        return p;
    }
    
    private void printList(ArrayList<Position> list){
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println(" ->"+list.get(i).toString());
        }
    }
    
    
    
    
    
    
    
}
