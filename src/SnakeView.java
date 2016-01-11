
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kien
 */
public class SnakeView extends JFrame {

    public JButton startBut = new JButton("Start Game");
    public JButton quitBut = new JButton("Quit");
    public JButton howBut = new JButton("How To Play");
    public BufferedImage backGroundImage, appleImage, bananaImage, cherryImage, bodyImage, headImage, starImage;

    public JFrame gameScreen;
    public JFrame menuScreen;
    public Dimension dimension;
    public JPanel background;
    public JPanel menu;
    SnakeModel model;
   

    public SnakeView(SnakeModel model) throws IOException {
        backGroundImage = ImageIO.read(getClass().getResourceAsStream("/sand.jpg"));
        starImage = ImageIO.read(getClass().getResourceAsStream("/star-night-sky.jpg"));
        this.model = model;
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        model.isPaused = true;
        
        
        menuScreen = new JFrame("Menu");
        menuScreen.setVisible(true);
        menuScreen.setSize(805, 700);
        menuScreen.setResizable(false);
        menu = new Menu();
      
        menu.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;

        startBut.setPreferredSize(new Dimension(140,40));
        quitBut.setPreferredSize(new Dimension(140,40));
        howBut.setPreferredSize(new Dimension(140,40));
         
       

        
        menu.add(startBut,gbc);
        menu.add(quitBut,gbc);
        menu.add(howBut,gbc);
        menu.setBackground(Color.BLACK);
       
        menuScreen.add(menu);
        
        howBut.addActionListener(new ActionListener(){     
            @Override
            public void actionPerformed(ActionEvent e) {
                 
               JOptionPane.showMessageDialog(menuScreen,
                "Please click start game to start the game. "
                        + "\nInitially, the game is paused. "
                        + "\nPlease press space key to unpause. "
                        + "\nYou can pause the game at anytime by pressing space bar."
                        + "\n If you die at anytimes, please press space bar to replay "
                        + "\nPress Quit button to quit the game"
               );
            }
        });
        
        quitBut.addActionListener(new ActionListener(){     
            @Override
            public void actionPerformed(ActionEvent e) {   
               System.exit(0);
            }
        });
        
        
        
        gameScreen = new JFrame();
        gameScreen.setSize(805, 700);
        gameScreen.setResizable(false);
       
        background = new backGround();
        gameScreen.add(background);
        gameScreen.setLocation(dimension.width / 2 - gameScreen.getWidth() / 2, dimension.height / 2 - gameScreen.getHeight() / 2);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         startBut.addActionListener(new ActionListener(){     
            @Override
            public void actionPerformed(ActionEvent e) {
                 menuScreen.setVisible(false);
                 gameScreen.setVisible(true);
            }
        });
         
         
         
    }

    
    
    @Override
    public void addKeyListener(KeyListener keyListener) {
        gameScreen.addKeyListener(keyListener);
    }
    
    class Menu extends JPanel{
          @Override
          protected void paintComponent(Graphics g) {
            g.drawImage(starImage, WIDTH, HEIGHT, null);
          }
    }
    
    
    
    
    
    class backGround extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            //paint background color
            //g.setColor(Color.BLACK);

            //g.fillRect(0, 0, 800, 700);
            g.drawImage(backGroundImage, WIDTH, HEIGHT, null);

            //paint the snake
            for (Point point : model.getSnakeParts()) {
                try {

                    bodyImage = ImageIO.read(getClass().getResourceAsStream("/Snake/body.png"));
                } catch (IOException ex) {
                    Logger.getLogger(SnakeView.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(bodyImage, point.x * 10, point.y * 10, 10, 10, null);
            }
            try {
                headImage = ImageIO.read(getClass().getResourceAsStream("/Snake/head.png"));
            } catch (IOException ex) {
                Logger.getLogger(SnakeView.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawImage(headImage, model.getSnakeHead().x * 10, model.getSnakeHead().y * 10, 10, 10, null);

            // PAINT food
            if (model.getFood().type.equals("normal")) {
               
                try {

                    cherryImage = ImageIO.read(getClass().getResourceAsStream("/food/cherry.png"));
                   
                } catch (IOException ex) {
                    Logger.getLogger(SnakeView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                g.drawImage(cherryImage, model.initFood.getLocation().x * 10, model.initFood.getLocation().y * 10, 15, 15, null);
              
            } 
            if (model.getFood().type.equals("uncommon")) {
                try {

                    bananaImage = ImageIO.read(getClass().getResourceAsStream("/food/banana.png"));
                } catch (IOException ex) {
                    Logger.getLogger(SnakeView.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(bananaImage, model.initFood.getLocation().x * 10, model.initFood.getLocation().y * 10, 15, 15, null);
            }
            if (model.getFood().type.equals("rare")) {
                try {

                    appleImage = ImageIO.read(getClass().getResourceAsStream("/food/apple.png"));
                } catch (IOException ex) {
                    Logger.getLogger(SnakeView.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(appleImage, model.initFood.getLocation().x * 10, model.initFood.getLocation().y * 10, 15, 15, null);
            }

            //show informations at the top 
            String infomation = "Score: " + model.getScore() + ", Length: " + model.snakeObject.snakeLength + ", Time: " + model.getTime() / 20;

            g.setColor(Color.BLACK);

            g.drawString(infomation, (int) (getWidth() / 2 - infomation.length() * 2.5f), 20);

            infomation = "Game Over!";

            if (model.isOver() == true) {
                g.drawString(infomation, (int) (getWidth() / 2 - infomation.length() * 2.5f), (int) dimension.getHeight() / 4);
            }

            infomation = "Paused!";

            if (model.isPaused() && !model.isOver()) {
                g.drawString(infomation, (int) (getWidth() / 2 - infomation.length() * 2.5f), (int) dimension.getHeight() / 4);
            }
        }
    }
}
