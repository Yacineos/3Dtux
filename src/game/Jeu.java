/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import java.util.ArrayList;

/**
 *
 * @author oukkaly
 */
public class Jeu {
    
    private Env env ;
    private Room room ; 
    private Profil profil;
    private ArrayList<Letter> lettres;
    
    public Jeu(){ 
        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        room = new Room();

        // Règle la camera
        env.setCameraXYZ(50, 60, 275);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil();
        
        lettres = new ArrayList<>();
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
        Tux tux = new Tux(env , room);
        env.addObject(tux);
     
        // instancie une lettre pour la tester 
        Letter letterN = new Letter('n',30.0,20.0,env , room);
        lettres.add(letterN);
        Letter letterI = new Letter('i',50,20.0,env , room);
        lettres.add(letterI);
        Letter letterA = new Letter('a',95,20.0,env , room);
        lettres.add(letterA  );
        Letter letterSpace = new Letter(' ',10,40.0,env , room);
        lettres.add(letterA  );
        env.addObject(letterN);
        env.addObject(letterI);
        env.addObject(letterA);
        env.addObject(letterSpace);
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        demarrepartie(partie);
         
        // Boucle de jeu
        Boolean finished;
        finished = false;
        

        
        while (!finished) {
 
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
 
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            // ... (sera complété plus tard) ...
               tux.deplace();
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
 
    }    
    
    protected void demarrepartie(Partie partie){
        
    }
    
    protected void appliqueRegles(Partie partie){
        
    }
    
    protected void terminePartie(Partie partie){
        
    }
    
    
}
