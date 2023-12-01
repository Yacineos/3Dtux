/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;

/**
 *
 * @author oukkaly
 */

// class qui permet de représenté une lettre dans l'environnement 3D
public class Letter extends EnvNode{
    private char letter ;
    private final Env env;
    private final Room room;
    
    public Letter(char I , double x , double y , Env env , Room room){
        this.env = env ;
        this.room = room ;
        setScale(3.0);
        setX(room.getWidth()-x);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(room.getDepth()-y); // positionnement au milieu de la profondeur de la room
        if(I==' '){
            setTexture("models/letter/cube.png");
        }else{
            setTexture("models/letter/"+I+".png");
        }
        setModel("models/letter/cube.obj");
    }
    
    
}
