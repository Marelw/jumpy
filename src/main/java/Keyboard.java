import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class helps listen after physical keys pressed on users keyboard
 * For use in game menu
 */

public class Keyboard implements KeyListener {

    private static Keyboard keyEvent;
    
    
    private boolean [] keystrokes;
    
    /**
     * Constructor sets boolean array to max of keyboards keycode value
     */
    private Keyboard() {
        keystrokes = new boolean[525]; // vad är max keycode value??
    }
    
    public void keyTyped(KeyEvent e) {
        //   do nothing here!!
        //KeyTypes = Invoking when a key is typed. Uses KeyChar, char output
    }
    
    public void keyPressed(KeyEvent e) {
        //KeyPressed = Invoked when a psysical key is pressed down. uses KeyCode, int output
        //KeyCode = each buttoms individual number
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keystrokes.length) {
            keystrokes[e.getKeyCode()] = true;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        //KeyReleased = called whenever a pressed down key is released
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keystrokes.length) {
            keystrokes[e.getKeyCode()] = false;
        }
        
    }

    /**
     * 
     * @param key
     * @return false if key is not pressed, returns true if key is pressed
     */
    public boolean isKeyPressed(int key) {

        if (key >= 0 && key < keystrokes.length) {
            return keystrokes[key];
        }

        return false;
    }
    
    /**
     * 
     * @return instace of keybord event
     */
    public static Keyboard getKeyEvent() {

        if (keyEvent == null) {
            keyEvent = new Keyboard();
        }

        return keyEvent;
    }
    
}