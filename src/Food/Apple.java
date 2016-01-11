package Food;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kien
 */
public class Apple extends Food {
    public Apple (){
    point = 30;
    type = "rare";
    location = new Point(0,2);
}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(location.x , location.y , 10, 10);
    }
}
