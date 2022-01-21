
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

    /**
     * Bird constants
     */
    public final int BIRD_HEIGHT = 25;
    public final int BIRD_WIDTH = 39;
    private final int STARTING_BIRD_X_LOC = 80;
    private final int STARING_BIRD_Y_LOC = 320;

    /**
     * Default bird constructor
     * @return 
     */
    public Birb(int birdWidth, int birdHeight) {
        jumpyBirb = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("lib/bluejumpy.png"));
        scaleBird(birdWidth, birdHeight);
    }

    /**
     * Method to scale bird
     */
    public void scaleBird(int width, int height) {
        jumpyBirb = jumpyBirb.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Method to get rectangle that outlines the bird's image
     * @return rectangle outline of bird on screen
     */
    public Rectangle getRectangle() {
        return (new Rectangle(STARTING_BIRD_X_LOC, STARING_BIRD_Y_LOC, jumpyBirb.getWidth(null), jumpyBirb.getHeight(null)));
    }

    /**
     * Method to get a buffered image that represents the bird object
     * @return bird bufferdd image object
     */
    public BufferedImage getBufferedImage() {
        BufferedImage bi = new BufferedImage(jumpyBirb.getWidth(null), jumpyBirb.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.drawImage(jumpyBirb, 0, 0, null);
        g.dispose();
        return bi;
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