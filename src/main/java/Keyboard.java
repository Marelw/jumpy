import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public void keyTyped(KeyEvent e) {
        //   do nothing here!!
        //KeyTypes = Invoking when a key is typed. Uses KeyChar, char output
    }

    public void keyPressed(KeyEvent e) {
        //KeyPressed = Invoked when a psysical key is pressed down. uses KeyCode, int output
        //KeyCode = each buttoms individual number
        
    }

    public void keyReleased(KeyEvent e) {
        //KeyReleased = called whenever a pressed down key is released
        System.out.println("You released key char: " + e.getKeyChar());
    
    }
    
}