/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author oukkaly
 */

// class Room c'est la boite où le tux sera limité dans ses déplacements et où les lettres sont générées 
// cette derniere est parsé à l'aide d'un parseur DOM
public class Room {
    
    
    private int depth ;
    private int height ;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;
    private String textureSouth;
    
    public Room(){
        /*
        this.depth = 100;
        this.width = 100;
        this.height = 60;
        this.textureBottom = "textures/skybox/default/bottom.png";
        this.textureNorth = "textures/skybox/default/north.png";
        this.textureEast = "textures/skybox/default/east.png";
        this.textureWest = "textures/skybox/default/west.png";
        */
        parsingDOMEnvironnement();
    }
    
    private void parsingDOMEnvironnement(){
        try{
            Document _doc=XMLUtil.DocumentFactory.fromFile("src/partie_XML/xml/plateau.xml");
            this.height=Integer.parseInt(_doc.getElementsByTagName("height").item(0).getTextContent());
            this.width=Integer.parseInt(_doc.getElementsByTagName("width").item(0).getTextContent());
            this.depth=Integer.parseInt(_doc.getElementsByTagName("depth").item(0).getTextContent());
            this.textureBottom=_doc.getElementsByTagName("textureBottom").item(0).getTextContent();
            this.textureNorth=_doc.getElementsByTagName("textureNorth").item(0).getTextContent();
            this.textureEast=_doc.getElementsByTagName("textureEast").item(0).getTextContent();
            this.textureWest=_doc.getElementsByTagName("textureWest").item(0).getTextContent();
        }catch(Exception e){
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, e);
        }
       
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getTextureBottom() {
        return textureBottom;
    }

    public String getTextureNorth() {
        return textureNorth;
    }

    public String getTextureEast() {
        return textureEast;
    }

    public String getTextureWest() {
        return textureWest;
    }

    public String getTextureTop() {
        return textureTop;
    }

    public String getTextureSouth() {
        return textureSouth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTextureBottom(String textureBottom) {
        this.textureBottom = textureBottom;
    }

    public void setTextureNorth(String textureNorth) {
        this.textureNorth = textureNorth;
    }

    public void setTextureEast(String textureEast) {
        this.textureEast = textureEast;
    }

    public void setTextureWest(String textureWest) {
        this.textureWest = textureWest;
    }

    public void setTextureTop(String textureTop) {
        this.textureTop = textureTop;
    }

    public void setTextureSouth(String textureSouth) {
        this.textureSouth = textureSouth;
    }
    
    
    
}