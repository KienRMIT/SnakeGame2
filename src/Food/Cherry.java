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
public class Cherry extends Food {
    public Cherry () {
        point = 10;
        type = "normal";
        location = new Point(0,2);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(location.x , location.y , 10, 10);
    }
}
