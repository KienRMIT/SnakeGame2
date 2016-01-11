package Food;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Hashtable;

/**
 *
 * @author Kien
 */

public class FoodCache {
    private static Hashtable<Integer, Food> foodMap = new Hashtable<Integer, Food>();
    
    public static Food getFood (int foodID) {
        Food cachedFood =  foodMap.get(foodID);
        return (Food) cachedFood.clone();
    }
    
    public static void loadCache(){
        Cherry cherry = new Cherry();
        cherry.setID(1);
        foodMap.put(cherry.getID(), cherry);
        Banana banana = new Banana();
        banana.setID(2);
        foodMap.put(banana.getID(), banana);
        Apple apple = new Apple();
        apple.setID(3);
        foodMap.put(apple.getID(), apple);
        
    }
}
