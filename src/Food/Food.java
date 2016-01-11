package Food;



import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kien
 */
public abstract class Food implements Cloneable {
    private int id;
    public int point;
    public String type;
    public Point location;
    
    abstract void draw(Graphics g);
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    public int getPoint() {
        return point;
    }
    
    public String getType() {
        return type;
    }
    
    public Point getLocation() {
        return location;
    }
    
    public void setLocation() {
        Random random = new Random();
        location = new Point(random.nextInt(79),random.nextInt(66));
    }
    
    
    @Override
    public Object clone() {
        Object clone = null;
         
        try {
            clone = super.clone();
            
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
