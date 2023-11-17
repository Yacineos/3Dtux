/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
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
        setScale(37.0);
        setX(room.getWidth()/2);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(room.getDepth()/2); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/musk.png");
        setModel("models/tux/tux.obj");
    }
    
    public void deplace(){
        if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(180);
            this.setZ(this.getZ() - 1.0);
        }
        if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(270);
            this.setX(this.getX() - 1.0);
       }
        if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(360);
            this.setZ(this.getZ() + 1.0);
        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(90);
            this.setX(this.getX() + 1.0);
       }
    }
    
}
