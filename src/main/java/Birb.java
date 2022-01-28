import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;

public class Birb extends JFrame {

    /**
     * Bird attributes
     */
    //private boolean isAlive = true;
    //public double velocity;
    //public double gravity;
    //private int keyResponseDeley;
    //private double birdShift;


    /**
     * Bird constants
     */
    public final int BIRB_HEIGHT = 100;
    public final int BIRB_WIDTH = 150;
    private int STARTING_BIRB_X_LOC = 100;
    private int STARING_BIRB_Y_LOC = 150;
    private JLabel blueJumpy;

    
    private ImageIcon image;
    //private Keyboard keyboard; // bryt ut Keyboard response till egen



    public Birb(ImageIcon image, int x, int y) {
        this.STARTING_BIRB_X_LOC = x;
        this.STARING_BIRB_Y_LOC = y;
        this.image = image;
        setupBirb();
    }

    public int getSTARTING_BIRB_X_LOC() {
        return STARTING_BIRB_X_LOC;
    }
    public int getSTARING_BIRB_Y_LOC() {
        return STARING_BIRB_Y_LOC;
    }

    public JLabel getBirb(){
        return this.blueJumpy;
    }

    public void setupBirb(){
        blueJumpy = new JLabel();
        blueJumpy.setBounds(this.STARTING_BIRB_X_LOC, this.STARING_BIRB_Y_LOC, BIRB_WIDTH, BIRB_HEIGHT);
        blueJumpy.setIcon(this.image);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    @Override
    public void paint(Graphics g) {
    }

    // public Rendering getRender() {
    //     Rendering ren = new Rendering(100, 150, "lib/bluejumpy.png");
        
    //     if (image == null) {
    //         image = Utilities.loadImage("lib/bluejumpy.png");
    //     }
        
    //     ren.imageRender = image;

    //     return ren;
    // }

    
    /**
     * @return If bird is alive
     */
    public boolean isAlive() {
        return true;
    }
    
    /**
     * Kills bird
     */
    
    // public void kill() {
    //     isAlive = false;
    // }
    
}