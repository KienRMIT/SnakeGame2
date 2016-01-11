/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author TheTai
 */
public class SingleSnakeObject {

   //create an object of SingleObject
   private static SingleSnakeObject snakeObject = new SingleSnakeObject();
 
   public ArrayList<Point> snakeParts = new ArrayList<Point>();
   public int snakeLength;
   public Point snakeHead;
   
   
   //make the constructor private so that this class cannot be
   //instantiated
   private SingleSnakeObject(){
       snakeHead = new Point(0,-1);
   }

   //Get the only object available
   public static SingleSnakeObject getInstance(){
      return snakeObject;
   }
   
}
