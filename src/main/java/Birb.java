
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Birb {

    /**
     * Bird attributes
     */
    private boolean isAlive = true;
    private Image jumpyBirb;
    public double velocity;
    public double gravity;
    private int keyResponseDeley;
    private double birdShift;


    /**
     * Bird constants
     */
    public final int BIRD_HEIGHT = 25;
    public final int BIRD_WIDTH = 39;
    private final int STARTING_BIRD_X_LOC = 100;
    private final int STARING_BIRD_Y_LOC = 150;
    private Image image;
    private Keyboard keyboard; // bryt ut Keyboard response till egen klass

    public Birb() {
    }

    /**
     * Getter for JumpyBirb object
     */
    public Image getBird() {
        return jumpyBirb;
    }
    /**
     * @return If bird is alive
     */
    public boolean isAlive() {
        return true;
    }

    /**
     * Kills bird
     */
    public void kill() {
        isAlive = false;
    }

}