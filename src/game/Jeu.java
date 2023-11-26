/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.lwjgl.input.Keyboard;
import org.xml.sax.SAXException;

/**
 *
 * @author gladen
 */
public abstract class Jeu {

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }
    protected final Env env ;
    private Profil profil;
    private ArrayList<Letter> lettres;
    private ArrayList<Position> listPositionLettres ;
    private String mot ;
    private final Dico dico;
    private Tux tux;
    private final Room mainRoom;
    private final Room menuRoom;
    
    
    protected EnvTextMap menuText;                         //text (affichage des texte du jeu)
    
    
    
    public Jeu() {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        mainRoom = new Room();

        // Instancie une autre Room pour les menus
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/black.png");
        menuRoom.setTextureWest("textures/black.png");
        menuRoom.setTextureNorth("textures/black.png");
        menuRoom.setTextureBottom("textures/black.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil();
        
        // Dictionnaire
        dico = new Dico("");
        
        try {
            dico.lireDictionnaireDOM("src/partie_XML/","dico.xml" );
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        // instancie le menuText
        menuText = new EnvTextMap(env);
        
        // Textes affichés à l'écran
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);
    
        
        this.mot = dico.getMotDepuisListNiveaux(1);
        System.out.println(""+mot);
        
        
        listPositionLettres = new ArrayList<>();
        lettres = new ArrayList<>();
        
        
        fillLettersPositionList(mot.length());
        
        for(int i = 0 ; i < mot.length(); i++){
            Letter l = new Letter(mot.charAt(i), listPositionLettres.get(i).getX(), listPositionLettres.get(i).getY(), env, mainRoom);
            lettres.add(l);
        }
        
        printList(listPositionLettres);

    }

    /**
     * Gère le menu principal
     *
     */
    public void execute() {

        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);
        env.exit();
    }


    // fourni
    private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }

    
    // fourni, à compléter
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
            // affiche menu
            menuText.getText("Question").display();
            menuText.getText("Jeu1").display();
            menuText.getText("Jeu2").display();
            menuText.getText("Jeu3").display();
            menuText.getText("Jeu4").display();
            
            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // nettoie l'environnement du texte
            menuText.getText("Question").clean();
            menuText.getText("Jeu1").clean();
            menuText.getText("Jeu2").clean();
            menuText.getText("Jeu3").clean();
            menuText.getText("Jeu4").clean();

            // restaure la room du jeu
            env.setRoom(mainRoom);

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    // .......... dico.******
                    // crée un nouvelle partie
                    partie = new Partie("2018-09-7", "test", 1);
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    partie = new Partie("2018-09-7", "test", 1); //XXXXXXXXX
                    // Recupère le mot de la partie existante
                    // ..........
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_3:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_4:
                    playTheGame = MENU_VAL.MENU_SORTIE;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
               
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();
                // charge le profil de ce joueur si possible
                /*
                if (profil.charge(nomJoueur)) {
                    choix = menuJeu();
                } else {
                    
                    choix = MENU_VAL.MENU_SORTIE;//CONTINUE;
                }
                */
                //bricolage
                choix = MENU_VAL.MENU_SORTIE;//CONTINUE;
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                // crée un profil avec le nom d'un nouveau joueur
                //profil = new Profil(nomJoueur);
                profil = new Profil();
                choix = menuJeu();
                break;

            // -------------------------------------
                // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                choix = MENU_VAL.MENU_SORTIE;
        }
        return choix;
    }

    public void joue(Partie partie) {

        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(tux);

        Arrays.asList(this.lettres.get(0), this.lettres.get(1), this.lettres.get(2), this.lettres.get(3)).forEach(env::addObject);


        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        demarrePartie(partie);

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
            tux.deplace();

            // Ici, on applique les regles
            appliqueRegles(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected abstract void demarrePartie(Partie partie);

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
            Position p = generateRandomCoor(this.mainRoom.getWidth() , this.mainRoom.getDepth()) ;
            //System.out.println("on est pas entré dans la boucle "+ p.toString());
            // TantQue la position générée est dans la liste on en génère une autre 
            while(!done){
                //System.out.println("on est entré dans la boucle "+ p.toString());
                if(distanceEntreLeResteDesLettres(p)){
                    //System.out.println("est ce que la position :"+p.toString()+" est dans la liste :"+listPositionLettres.contains(p));
                    this.listPositionLettres.add(p);
                    done = true ;
                }else{
                    p = generateRandomCoor(this.mainRoom.getWidth() , this.mainRoom.getDepth()) ;
                    //System.out.println("ohhh je genere une nouvelle :"+p.toString()+" est dans la liste ");

                }
            }
        }
        
    }
    
    // generates a random coordonates that are between (0 and maxX ) && ( 0 and maxY)
    protected Position generateRandomCoor(int maxX , int maxY){
        Position p = new Position();
        p.setY((Math.random()*1000) % (maxY-20));
        p.setX((Math.random()*1000) % (maxX-20));
        return p;
    }
    
    private boolean distanceEntreLeResteDesLettres(Position p){
        boolean estEnCollisionAvecUneAutreLettre = false ;
        int i = 0 ;
        while(i < listPositionLettres.size() && !listPositionLettres.get(i).samePosition(p)){
            i++;
        }
        return  !(i < listPositionLettres.size()) ;
    }
    
    private void printList(ArrayList<Position> list){
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println(" ->"+list.get(i).toString());
        }
    }
}
