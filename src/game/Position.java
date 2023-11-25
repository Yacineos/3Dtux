/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author oukkaly
 */
public class Position {
    private double x ;
    private double y ;

    public Position() {
    }
    
    

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    public boolean samePosition(Position p){
        return distance(p) < 10.0;
    }

    protected double distance(Position p){
        double dist = Math.sqrt(Math.pow(( this.getX()- p.getX()),2) + Math.pow(this.getY()-p.getY(), 2));
        return dist;
    }
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }
    
    
    
    
    
}
