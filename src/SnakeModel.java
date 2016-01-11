
import Food.Food;
import Food.FoodCache;
import Snake.SingleSnakeObject;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.*;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;
import static java.lang.Math.random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kien
 */
public class SnakeModel {
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public Random random = new Random();
    private  boolean isOver = false;
    public boolean isPaused = false;
    public int musicState = 1;
    SingleSnakeObject snakeObject = SingleSnakeObject.getInstance();
    Food initFood= createFood(1 + random.nextInt(3));
    
    
    public SnakeModel() throws LineUnavailableException, UnsupportedAudioFileException {
        startGame(); 
    }
    
    public Food getFood () {
        return initFood;
    }
    
    public Food setFood(){
        return initFood = createFood(1 + random.nextInt(3));
    }
    
    public static Food createFood(int foodID)  {
        FoodCache.loadCache();
        switch (foodID) {
            case 1:
                Food cherry = (Food) FoodCache.getFood(foodID);
                return cherry;
            case 2:
                Food banana = (Food) FoodCache.getFood(foodID);
                return banana;
            case 3:
                Food apple = (Food) FoodCache.getFood(foodID);
                return apple;
            default:
                return null;
        }
    }
    
    private int ticks = 0, direction = DOWN, score, time;
        
    public int getDirection() {
        return direction;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void setIsOver (boolean isOver) {
        this.isOver = isOver;
    }
    
    public boolean isOver() {
        return isOver;
    }
    
    public boolean isPaused(){
        return isPaused;
    }
    
    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }
    
    public void setTicks() {
        this.ticks = ticks++;
    }
    
    public int getTicks() {
        return ticks;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int score){
        this.score = this.score + score;
    }
    
    public int getTime(){
        return time;
    }
    public void setTime(){
        this.time++;
    }
    
    public int getSnakeLength() {
       return snakeObject.snakeLength;
   }
   
   public void addSnakeLength() {
       snakeObject.snakeLength++;
   }
   
   public Point getSnakeHead() {
       return snakeObject.snakeHead;
   }
   
   public void setSnakeHead(Point snakeHead) {
       snakeObject.snakeHead = snakeHead;
   }
   
   public ArrayList<Point> getSnakeParts() {
       return snakeObject.snakeParts;
   }
    
    public void startGame() throws LineUnavailableException, UnsupportedAudioFileException{
        if (musicState == 1){
            music();
            musicState = 2;
        }
        isOver = false;
	isPaused = false;
	time = 0;
	score = 0;
	ticks = 0;
	direction = DOWN;
        snakeObject.snakeParts.clear();
        snakeObject.snakeLength = 10;
    }
    
    public boolean headTouchTail(int x, int y)
	{
		for (Point point : snakeObject.snakeParts)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
    
    public void randomFood (Point food) {
        food.setLocation(random.nextInt(79), random.nextInt(66));
    }
    
    public static void music() throws LineUnavailableException, UnsupportedAudioFileException {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        try {
           Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("music.wav")));
            clip.start();
        } catch (IOException error) {
            System.out.println("file not found");
        }
        MGP.start(loop);
    }
    
    
}
