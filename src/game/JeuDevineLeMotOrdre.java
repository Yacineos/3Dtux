/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author OUKKAL Yacine
 */

/*
Lorsque les classes Jeu et JeuDevineLeMotOrdre seront complètement fonctionnelles :

    -un profil sera actif
    -une nouvelle partie sera générée depuis ce profil et un mot (String) sera choisi à partir du dictionnaire et associé à la (enregistré) partie
    -le lettres de ce mot seront affichées dans l'environnement
    -la partie sera transmise à à la méthode démarrePartie qui initialisera le compteur de temps notamment, mais également le nombre de lettres restantes
    -la boucle de jeu commencera et les règles de jeu (que faire quand une lettre est trouvée, vérifier le chronomètre ...) seront appliquées grâce à la méthode appliqueRègles
    -une fois le jeu terminé (selon les règles), la méthode terminePartie sera appelée. Elle se chargera d'arrêter le temps s'il n'est pas déjà terminé et d'enregistrer les résultats dans la partie.
*/
public class JeuDevineLeMotOrdre extends Jeu {
    private int nbLettersRestantes ; //qui permet de stocker le nombre de lettres restantes
    private Chronometre chrono ; //  pour gérer le temps de jeu
    
    //Le constructeur de cette classe devra appeler le constructeur de la classe de base Jeu.
    public JeuDevineLeMotOrdre(){
        
    }
    
    protected void demarrepartie(Partie partie){
           
    }
    protected void appliqueRegles(Partie partie){
        
    }
    protected void terminePartie(Partie partie){
        
    }
    private boolean tuxTrouveLettre(){
        return false ;
    }
    
    private int getNbLettresRestantes(){
        return 0;
    }
    
    private int getTemps(){
        return 0 ;
    }

}
