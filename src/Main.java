
import java.io.IOException;
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
public class Main {
    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        SnakeController controller = new SnakeController(view, model);
       
        
    }
}
