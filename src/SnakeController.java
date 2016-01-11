
import Snake.SingleSnakeObject;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kien
 */
public class SnakeController implements ActionListener {

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public Timer timer = new Timer(20, this);
    SnakeView view;
    SnakeModel model;

    public SnakeController(SnakeView view, SnakeModel model) {
        this.view = view;
        this.model = model;
        view.addKeyListener(new customKeyListener());
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        view.background.repaint();

        model.setTicks();

        if (model.getTicks() % 2 == 0 && model.getSnakeHead() != null && !model.isOver() && !model.isPaused()) {
            model.setTime();
            model.getSnakeParts().add(new Point(model.getSnakeHead().x, model.getSnakeHead().y));
            Point tempHead = model.getSnakeHead();

            if (model.getDirection() == UP) {
                if (model.getSnakeHead().y - 1 >= 0 && model.headTouchTail(model.getSnakeHead().x, model.getSnakeHead().y - 1)) {
                    tempHead.y--;
                    model.setSnakeHead(tempHead);
                } else {
                    model.setIsOver(true);
                }
            }

            if (model.getDirection() == DOWN) {
                if (model.getSnakeHead().y + 1 < 67 && model.headTouchTail(model.getSnakeHead().x, model.getSnakeHead().y + 1)) {
                    tempHead.y++;
                    model.setSnakeHead(tempHead);
                } else {
                    model.setIsOver(true);
                }
            }

            if (model.getDirection() == LEFT) {
                if (model.getSnakeHead().x - 1 >= 0 && model.headTouchTail(model.getSnakeHead().x - 1, model.getSnakeHead().y)) {
                    tempHead.x--;
                    model.setSnakeHead(tempHead);
                } else {
                    model.setIsOver(true);
                }
            }

            if (model.getDirection() == RIGHT) {
                if (model.getSnakeHead().x + 1 < 80 && model.headTouchTail(model.getSnakeHead().x + 1, model.getSnakeHead().y)) {
                    tempHead.x++;
                    model.setSnakeHead(tempHead);
                } else {
                    model.setIsOver(true);
                }
            }

            if (model.getSnakeParts().size() > model.getSnakeLength()) {
                model.getSnakeParts().remove(0);

            }

            if (model.getFood() != null) {
                
                if (model.getSnakeHead().getLocation().equals(model.getFood().getLocation())) {
                    
                    if (model.getFood().type.equals("normal")) {
                        model.setScore(model.getFood().getPoint());
                        model.addSnakeLength();
                        model.setFood();
                        model.getFood().setLocation();
                       
                    }
                    if (model.getFood().type.equals("uncommon")) {
                        model.setScore(model.getFood().getPoint());
                        model.addSnakeLength();
                        model.setFood();
                        model.getFood().setLocation();
                        
                    }
                    if (model.getFood().type.equals("rare")) {
                        model.setScore(model.getFood().getPoint());
                        model.addSnakeLength();
                        model.setFood();
                        model.getFood().setLocation();
                        
                    }
                    
                    
                }
            }
        }
    }

    class customKeyListener implements KeyListener {

        public void keyPressed(KeyEvent e) {
            int i = e.getKeyCode();

            if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && model.getDirection() != RIGHT) {
                model.setDirection(LEFT);
            }

            if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && model.getDirection() != LEFT) {
                model.setDirection(RIGHT);
            }

            if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && model.getDirection() != DOWN) {
                model.setDirection(UP);
            }

            if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && model.getDirection() != UP) {
                model.setDirection(DOWN);
            }

            if (i == KeyEvent.VK_SPACE) {
                if (model.isOver()) {
                    try {
                        model.startGame();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(SnakeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(SnakeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    model.isPaused = !model.isPaused;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

    }

}
