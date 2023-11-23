/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import env3d.EnvBasic;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author oukkaly
 */
public class Tux extends EnvNode{
    private Env env ;
    private Room room ;
    
    public Tux(Env env , Room room){
        this.env = env ;
        this.room = room ;
        setScale(16.0);
        setX(room.getWidth()/2);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(room.getDepth()/2); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/musk.png");
        setModel("models/tux/tux.obj");
    }
    
    public void deplace(){
        if ((env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP))&& !collisionProfonde() ) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(180);
            this.setZ(this.getZ() - 1.0);
          
        }
        if ((env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT) )&& !collisionAgauche()) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(270);
            this.setX(this.getX() - 1.0);
            
       }
        if ((env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN) )&& !collisionNonProfone()) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(360);
            this.setZ(this.getZ() + 1.0);
         
        }
        if ((env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT))&& !collisionADroite()) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(90);
            this.setX(this.getX() + 1.0);
           
       }
        if(env.getKeyDown(Keyboard.KEY_O)){
            env.soundPlay("sounds/ohShitNotGood.wav");            
        }
        if(env.getKeyDown(Keyboard.KEY_P)){
            env.soundPlay("sounds/caVaPeter.wav");            
        }
        
        if(env.getKeyDown(Keyboard.KEY_N)){
            env.soundPlay("sounds/NoNoNo.wav");            
        }
        
        
       //System.out.println("x : "+this.getX() + " , y : "+this.getZ());
    }
    
    /*
        this method return true if the Tux isin collision with the walls or with a Letter 
        @returns boolean 
    */
    private boolean collisionADroite(){
        
        if( this.getX() < this.room.getWidth()){
            return false ;
        }else{
            return true ;
        }   
    }
      private boolean collisionAgauche(){
        
        if( this.getX() > 0){
            return false ;
        }else{
            return true ;
        }   
    }
    private boolean collisionProfonde(){
        
        if( this.getZ() > 0){
            return false ;
        }else{
            return true ;
        }   
    }
    private boolean collisionNonProfone(){
        
        if( this.getZ() < this.room.getDepth() ){
            return false ;
        }else{
            return true ;
        }   
    }
    
    /*
        j'ai mon Tux qui toucbe les murs , ce que je peux faire :
            - gérer la collisiono dans las fonctions collision et changer la "taille" des murs , diminuer la surface comme ça le tux ne sera jamais en contact 
            - modifier la hitBox du Tux avec une méthode tuxPosition qui renvoie une position modifier de sorte y aura pas de collision ( me semble la plus correcte )  
    */
    
}
