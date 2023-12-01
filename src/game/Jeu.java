/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.lwjgl.input.Keyboard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author gladen
 */

// classe principal qui définie un jeu 
public abstract class Jeu {

    

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }
    protected final Env env ;
    private Profil profil;
    private ArrayList<Letter> lettres;
    private ArrayList<Position> listPositionLettres ;
    private String mot ;
    private int niveau ;
    private final Dico dico;
    private Tux tux;
    private final Room mainRoom;
    private final Room menuRoom;
    
    
    protected EnvTextMap menuText;//text (affichage des texte du jeu)
    
    
    
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
            dico.lireDictionnaireSAX("src/partie_XML/dico.xml" );
        } catch (SAXException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        // instancie le menuText
        menuText = new EnvTextMap(env);
        
        // Textes affichés à l'écran
        valeursPossibleTextAfficheEcran();

    }
    
    private void valeursPossibleTextAfficheEcran(){
        //menu Principal
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        // 3 eme page
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        menuText.addText("Entrer un mot anglais >3 lettres sans caractères spéciaux : ", "nomAInserer", 60, 300);
        // 4 eme page si création de nouveau profile
        menuText.addText("Entrez votre date de naissance au format ddmmyyyy: ", "Anniversaire", 120, 300);
        // 2 eme page
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Ajouter un mot au dictionnaire ?", "Principal3", 250, 240);
        menuText.addText("4. Sortir du jeu ?", "Principal4", 250, 220);
        // menu Jeu
        menuText.addText("Choisissez une difficulte : ", "Difficulte", 200, 300);
        menuText.addText("- Niveau 1 ", "Niveau1", 250, 280);
        menuText.addText("- Niveau 2 ", "Niveau2", 250, 260);
        menuText.addText("- Niveau 3 ", "Niveau3", 250, 240);
        menuText.addText("- Niveau 4 ", "Niveau4", 250, 220);
        menuText.addText("- Niveau 5 ", "Niveau5", 250, 200);
        
        
    }
    private void addTextMotATrouver(String mot){
        // ajout de l'affichage du mot à trouver
        menuText.addText("Le mot à trouver c'est : ", "motATrouver", 250, 280);
        menuText.addText(mot, "mot", 250, 240);
    }
    
    
    private void addTextCinqDernieresParties(ArrayList<Partie> cinqDernieresPartiesDuJoueur){
        menuText.addText("Choisissez une Partie : ", "ChoixPartie", 200, 300);
        // la plus récente
        for(int i = 0 ; i < cinqDernieresPartiesDuJoueur.size() ; i++ ){
            menuText.addText(""+(i+1)+"- "+cinqDernieresPartiesDuJoueur.get(cinqDernieresPartiesDuJoueur.size()-(i+1)).getDate()+" time :" +cinqDernieresPartiesDuJoueur.get(cinqDernieresPartiesDuJoueur.size()-(i+1)).getTemps(), "Partie"+(i+1), 250, 280-(20*i));
        }
        menuText.addText("-6 RETOUR ", "RetourChoixPartie", 200, 150);

        
    }
    
    
    
    private void displayMotATrouver() {
        menuText.getText("motATrouver").display();
        menuText.getText("mot").display();
    }
    
    private void displayDesCinqDernieresParties(int nbParties){
         menuText.getText("ChoixPartie").display();
        // menuText.getText("RetourChoixPartie").display();
        for(int i = 0 ; i < nbParties ; i++){
            menuText.getText("Partie"+(i+1)).display();
        }
    }
    private void displayMenuPrincipal(){
        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
        menuText.getText("Principal4").display();
    }
    
    private void displayMenuJeu(){
        menuText.getText("Question").display();
        menuText.getText("Jeu1").display();
        menuText.getText("Jeu2").display();
        menuText.getText("Jeu3").display();
        menuText.getText("Jeu4").display();
    }
    private void cleanDesCinqDernieresParties(int nbParties){
        menuText.getText("ChoixPartie").clean();
        for(int i = 0 ; i < nbParties ; i++){
            menuText.getText("Partie"+(i+1)).clean();
        }
         //menuText.getText("RetourChoixPartie").clean();
    }
    
    private void displayChoixNiveau(){
        menuText.getText("Difficulte").display();
        menuText.getText("Niveau1").display();
        menuText.getText("Niveau2").display();
        menuText.getText("Niveau3").display();
        menuText.getText("Niveau4").display();
        menuText.getText("Niveau5").display();
    }
    
    private void cleanMenuPrincipal(){
        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();
        menuText.getText("Principal4").clean();
    }
    
    private void cleanMenuJeu(){
        menuText.getText("Question").clean();
        menuText.getText("Jeu1").clean();
        menuText.getText("Jeu2").clean();
        menuText.getText("Jeu3").clean();
        menuText.getText("Jeu4").clean();
    }
    private void cleanChoixNiveau(){
        menuText.getText("Difficulte").clean();
        menuText.getText("Niveau1").clean();
        menuText.getText("Niveau2").clean();
        menuText.getText("Niveau3").clean();
        menuText.getText("Niveau4").clean();
        menuText.getText("Niveau5").clean();
    }
    private void cleanMotATrouver(){
        menuText.getText("motATrouver").clean();
        menuText.getText("mot").clean();
    }

    
    private int getChoixToucheAppuyeeTroisChoix(){
        int res = 0;
        while (!(res == Keyboard.KEY_1 || res == Keyboard.KEY_2 || res == Keyboard.KEY_3 )) {
                res = env.getKey();
                env.advanceOneFrame();
            }
        return res ;
    }
    private int getChoixToucheAppuyeeQuatreChoix(){
        int res = 0;
        while (!(res == Keyboard.KEY_1 || res == Keyboard.KEY_2 || res == Keyboard.KEY_3 || res == Keyboard.KEY_4)) {
                res = env.getKey();
                env.advanceOneFrame();
            }
        return res ;
    }
    private int getChoixToucheAppuyeeCinqChoix(){
        int res = 0;
        while (!(res == Keyboard.KEY_1 || res == Keyboard.KEY_2 || res == Keyboard.KEY_3 || res == Keyboard.KEY_4 || res == Keyboard.KEY_5)) {
            res = env.getKey();
            env.advanceOneFrame();
        }
        return res ;
    }
    private int getChoixToucheAppuyeeSixChoix(){
        int res = 0;
        while (!(res == Keyboard.KEY_1 || res == Keyboard.KEY_2 || res == Keyboard.KEY_3 || res == Keyboard.KEY_4 || res == Keyboard.KEY_5 || res == Keyboard.KEY_6)) {
            res = env.getKey();
            env.advanceOneFrame();
        }
        return res ;
    }
    
    /**
     * Gère le menu principal
     *
     */
    public void execute() {

        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            try {
                mainLoop = menuPrincipal();
            } catch (InterruptedException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    
    // lit un mot de l'entrée stendard et l'affiche au fur et à mesure de l'écriture et le renvoie une fois l'utilisateur entre Entrer
    private String getNomNouveauNom() {
        String nomAInserer = "";
        menuText.getText("nomAInserer").display();
        nomAInserer = menuText.getText("nomAInserer").lire(true);
        menuText.getText("nomAInserer").clean();
        return nomAInserer;
    }
    
    
    // fourni
    private String getAnniversaire() {
        String anniversaire = "";
        menuText.getText("Anniversaire").display();
        anniversaire = menuText.getText("Anniversaire").lire(true);
        menuText.getText("Anniversaire").clean();
        return anniversaire;
    }

    
    // fourni, à compléter
    private MENU_VAL menuJeu() throws InterruptedException {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
           
            // affiche le menu du jeu
            displayMenuJeu();
            
            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = getChoixToucheAppuyeeQuatreChoix();
            

            // nettoie l'environnement du texte
            cleanMenuJeu();

           

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    displayChoixNiveau();
                    //System.out.println("on est içi");
                    // vérifie qu'une touche 1, 2, 3 ,4 ou 5 est pressée
                    int touche2 = getChoixToucheAppuyeeCinqChoix();
                    
                    // remplie this.mot depuis le dico suivant le choix du niveau 
                    getMotDepuisListNiveauxSuivantTouchePressee(touche2);
                  
                    // clean l'écran des niveaux affichées
                    cleanChoixNiveau();
                    
                    System.out.println(""+mot);
                    
                    // génère des positions aléatoires et place les lettres dans la map 
                    
                    genererLettresMotRandomPosition();
                    
                    // .......... dico.******
                    addTextMotATrouver(mot);
                    
                    displayMotATrouver();
                    
                    env.advanceOneFrame();
                    TimeUnit.SECONDS.sleep(5);
                    env.advanceOneFrame();
                    
                    cleanMotATrouver();
                    
                    env.setRoom(mainRoom);
                    
                    // crée la date d'aujourd'hui au format yyyy-mm-dd
                    String datePartieFraichementCree = getCurrentDate();
                    // crée un nouvelle partie avec la date du jour ou la partie est crée , avec le mot parsé aléatoirement et avec le niveau qui correspond au mot 
                    partie = new Partie(datePartieFraichementCree, mot,niveau);
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    
                    profil.ajoueterPartie(partie);
                    profil.sauvegarder(profil.getNom());

                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    
                        // trouve le fichier xml correspondant au nom de joueur
                        //Document docXmlProfilJoueur = this.profil.fromXML(mot);
                        
                        //récupérer toutes les parties du joueurs 
                        ArrayList<Partie> partiesJoueur =  this.profil.getParties();
                        // récupère les 5 dernières parties 
                        ArrayList<Partie> cinqsDernieresParties = getCinqDernieresParties(partiesJoueur );
                        
                        // afficher à l'écran toutes les parties 
                        addTextCinqDernieresParties(cinqsDernieresParties);
                        displayDesCinqDernieresParties(cinqsDernieresParties.size());
                        // donner la main à l'utilisateur pour séléctionner
                        // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
                        int touche3 = getChoixToucheAppuyeeCinqChoix();

                        // charger la partie choisie
                        //si il y a au moins 1 partie 
                        if(cinqsDernieresParties.size()>0){
                           
                            switch(touche3){
                                case Keyboard.KEY_1:
                                    partie = cinqsDernieresParties.get(cinqsDernieresParties.size()-1);
                                    break;
                                case Keyboard.KEY_2:
                                    partie = cinqsDernieresParties.get(cinqsDernieresParties.size()-2);
                                    break;
                                case Keyboard.KEY_3:
                                    partie = cinqsDernieresParties.get(cinqsDernieresParties.size()-3);
                                    break;
                                case Keyboard.KEY_4:
                                    partie = cinqsDernieresParties.get(cinqsDernieresParties.size()-4);
                                    break;
                                case Keyboard.KEY_5:
                                    partie = cinqsDernieresParties.get(cinqsDernieresParties.size()-5);
                                    break;
                                default :
                                    partie = new Partie("01/12/2023","tate",1);
                                    break;
                            }
                            
                            
                                this.mot= partie.getMot();



                                 // génère des positions aléatoires et place les lettres dans la map 

                                genererLettresMotRandomPosition();

                                cleanDesCinqDernieresParties(cinqsDernieresParties.size());

                                // .......... dico.******
                                addTextMotATrouver(mot);

                                displayMotATrouver();

                                env.advanceOneFrame();
                                TimeUnit.SECONDS.sleep(5);
                                env.advanceOneFrame();

                                cleanMotATrouver();

                                env.setRoom(mainRoom);

                                // crée la date d'aujourd'hui au format yyyy-mm-dd
                                datePartieFraichementCree = getCurrentDate();
                                // crée un nouvelle partie avec la date du jour ou la partie est crée , avec le mot parsé aléatoirement et avec le niveau qui correspond au mot 
                                partie.setDate(datePartieFraichementCree);

                                // on joue à la partie rechargé
                                joue(partie);

                                profil.ajoueterPartie(partie);
                                profil.sauvegarder(profil.getNom());
                            
                            
                        }
                        
                        
                      // cleanDesCinqDernieresParties(cinqsDernieresParties.size());
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

    private MENU_VAL menuPrincipal() throws InterruptedException {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;
        String anniversaire;

        // restaure la room du menu
        env.setRoom(menuRoom);

        displayMenuPrincipal();
               
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = getChoixToucheAppuyeeQuatreChoix();

        cleanMenuPrincipal();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // est ce qu'on a trouvé un profil ?
                boolean success = false ;
                // tant que le profil entré n'est pas bon on redemande un autre profil
                do{
                    // demande le nom du joueur existant
                    nomJoueur = getNomJoueur();
                    // charge le profil de ce joueur si possible

                    try{
                        this.profil = new Profil(nomJoueur);
                        success= true ;
                    }catch(Exception e){
                        System.out.println("ligne 475 Jeu"+e);
                        success = false ;
                    }
                    if (success) {
                        choix = menuJeu();
                    } else {

                        choix = MENU_VAL.MENU_CONTINUE;//CONTINUE;
                    }

                }while(!success);
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                env.advanceOneFrame();
                // demander l'anniversaire et vérifier le input
                do{    
                    anniversaire = getAnniversaire();
                    System.out.println(""+anniversaire);
                    env.advanceOneFrame();
                }while(!isValidDate(anniversaire) || anniversaire.length()!=8);
                    
                    profil = new Profil(nomJoueur,formatDateString(anniversaire));
                try{
                    choix = menuJeu();
                }catch(Exception e){
                    System.out.println(""+e);
                }
                break;

                
                
                
            // -------------------------------------
            // Touche 3 : ajouter un nouveau mot
            // -------------------------------------
            case Keyboard.KEY_3:
            
                String motAAjouter="";
                // est ce que l'utilisateur à saisie un mot conforme ?
                success = false ;
                // tant que le mot entré n'est pas conforme on redemande un autre nom
                do{
                    // demande le nom à insérer dans le dico
                     motAAjouter= getNomNouveauNom();
                    // charge le profil de ce joueur si possible
                }while(!isValidEnglishWord(motAAjouter));
                    
                    // si le mot il est conforme on le rajoute au dictionnaire 
                        // init de EditeurDico et l'ajout du mot 
                        EditeurDico editDico = new EditeurDico("src/partie_XML/dico.xml");
                        switch(motAAjouter.length()){
                            // si le mot il fait moins de 3 char on fait rien , normalement c'est jamais le cas mais juste par mesure de sécurité 
                            case 0:
                            case 1:
                            case 2:
                                break;
                            case 3:
                            case 4:
                                editDico.editer(motAAjouter, 1);
                                break ;
                            case 5:
                            case 6:
                                editDico.editer(motAAjouter, 2);
                                break ;
                            case 7:
                            case 8:
                                editDico.editer(motAAjouter, 3);
                                break ;
                            case 9:
                            case 10:
                                editDico.editer(motAAjouter, 4);
                                break ;
                            default:
                                editDico.editer(motAAjouter, 5);
                                break;
                        }
                        
                        // revenir au menu principal
                                                
                    choix = MENU_VAL.MENU_CONTINUE;
                    break;


            // -------------------------------------
            // Touche 4 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_4:
                choix = MENU_VAL.MENU_SORTIE;
                break;
        }
        return choix;
    }

    public void joue(Partie partie) {

        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(tux);

        for(int i = 0 ; i < this.mot.length() ; i++){
            env.addObject(lettres.get(i));
        }

        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        demarrePartie(partie);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!(env.getKey() == 1) && appliqueRegles(partie)) {
            
            env.setCameraXYZ(this.tux.getX(), 60, this.tux.getZ()+100);

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            

            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.deplace();

            // Ici, on applique les regles
            

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected abstract void demarrePartie(Partie partie);

    protected abstract boolean appliqueRegles(Partie partie);

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
    
    
    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
           return false;
        }
    }
    
    private String formatDateString(String inputDate) {
        // Assuming inputDate is in the format "ddmmyyyy"
        return inputDate.substring(0, 2) + "/" + inputDate.substring(2, 4) + "/" + inputDate.substring(4);
    }
    
    // récupère les 5 dernieres parties parmi une liste de parties
    private ArrayList<Partie> getCinqDernieresParties(ArrayList<Partie> toutesLesPartiesDuJoueur){
        ArrayList<Partie> cinqsDernieresParties ;
        //récupérer les que 5 dernières parties 
        if(toutesLesPartiesDuJoueur.size() > 5){
            // Obtenir une sous-liste des 5 dernières parties
            cinqsDernieresParties = new ArrayList<>(toutesLesPartiesDuJoueur.subList(toutesLesPartiesDuJoueur.size() - 5, toutesLesPartiesDuJoueur.size()));
        }else{
            // on fait rien sinon
            cinqsDernieresParties = toutesLesPartiesDuJoueur;
        }
        return cinqsDernieresParties;
    }

    private void getMotDepuisListNiveauxSuivantTouchePressee(int touche){
        switch(touche){
            case Keyboard.KEY_1:
                this.mot = dico.getMotDepuisListNiveaux(1);
                this.niveau = 1 ;
                break ;
            case Keyboard.KEY_2:
                this.mot = dico.getMotDepuisListNiveaux(2);
                this.niveau = 2 ;
                break ;
            case Keyboard.KEY_3:
                this.mot = dico.getMotDepuisListNiveaux(3);
                this.niveau = 3 ;
                break ;
            case Keyboard.KEY_4:
                this.mot = dico.getMotDepuisListNiveaux(4);
                this.niveau = 4 ;
                break ;
            case Keyboard.KEY_5:
                this.mot = dico.getMotDepuisListNiveaux(5);
                this.niveau = 5 ;
                break ;
            default :
                break ;
        }
    }

    /*
     * genère des positions aléatoires et Déclare les lettres du this.mot suivant ces positions
    **/
    private void genererLettresMotRandomPosition(){
        listPositionLettres = new ArrayList<>();
        lettres = new ArrayList<>();

        // remplie une array list de positions aléatoirement générées 
        fillLettersPositionList(this.mot.length());

        // rajoute les lettres dans la arraylist this.letters
        for(int i = 0 ; i < mot.length(); i++){
            Letter l = new Letter(this.mot.charAt(i), listPositionLettres.get(i).getX(), listPositionLettres.get(i).getY(), env, mainRoom);
            lettres.add(l);
        }
    }
    
    /*
     *Method to get the current date in the format "yyyy-MM-dd"
    */
    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
    
    
    
    // Use a regular expression to check if the word contains only english letters
    private boolean isValidEnglishWord(String word) {
        return word.matches("^[a-zA-Z]{3,}$");
    }
    
    // méthode qui affiche dans le jeu le temps qui reste pour une partie 
    private void afficheTempsPartieRestant(){
        
    }
    
// test purpose

    public static void main(String[] args) {
       /*
        // Création d'un profil avec quelques parties
        Profil profil = new Profil("NomDuJoueur", "01/01/2000");
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot1", 1));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot2", 2));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot3", 3));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot4", 4));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot5", 5));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot6", 4));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot11", 1));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot22", 2));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot33", 3));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot43", 4));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot54", 5));
        profil.ajoueterPartie(new Partie("01/01/2021", "Mot65", 4));

        // Récupération de toutes les parties du joueur
        ArrayList<Partie> partiesJoueur = profil.getParties();
        ArrayList<Partie> cinqDernieresParties;

        // Récupération des 5 dernières parties
        if (partiesJoueur.size() > 5) {
            // Obtenir une sous-liste des 5 dernières parties
            cinqDernieresParties = new ArrayList<>(partiesJoueur.subList(partiesJoueur.size() - 5, partiesJoueur.size()));
        } else {
            // Si le joueur a moins de 5 parties, utiliser toutes les parties disponibles
            cinqDernieresParties = partiesJoueur;
        }

        // Affichage des 5 dernières parties (ou toutes les parties si moins de 5)
        System.out.println("Les 5 dernières parties (ou toutes les parties si moins de 5):");
        for (Partie partie : cinqDernieresParties) {
            System.out.println( "Mot: " + partie.getMot() + ", Niveau: " + partie.getNiveau());
        }
        */
    }
}
