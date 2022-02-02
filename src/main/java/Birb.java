import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Birb extends JPanel {

    /**
     * Bird attributes
     */
    //private boolean isAlive = true;
    //public double velocity;
    //public double gravity;
    //private int keyResponseDelay;
    //private double birdShift;

    /**
     * Bird constants
     */
    public final int HEIGHT = 400;
    public final int WIDTH = 700;
    private Rectangle birbPic;
    private BufferedImage birbImageSprite;
    private BufferedImage background;
    private int birbImageSpriteCount; //vad är detta till för??????
    //private Keyboard keyboard; // bryt ut Keyboard response till egen

    public Birb() {
        super();
        try {
            this.birbImageSprite = ImageIO.read(new File("lib/bird.png"));
            this.background = ImageIO.read(new File("lib/background.png"));
            this.birbImageSpriteCount = 0;
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }

        this.setBounds(10,10,WIDTH,HEIGHT);
        this.setOpaque(true);

        this.birbPic = new Rectangle(20,20, 65, 50 );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBirb(g2d);
    }

    private void drawBirb(Graphics2D g){
        final Dimension d = this.getSize();
        int offset = 46 * birbImageSpriteCount;
        g.drawImage(background, 0, 0, 500,
                500, 0, 0, 500,500, null);

        g.drawImage(birbImageSprite, birbPic.x, birbPic.y, birbPic.x + birbPic.width,
                birbPic.y + birbPic.height, 0, 0, birbPic.width,birbPic.height, null);
    }

    // public Rendering getRender() {
    //     Rendering ren = new Rendering(100, 150, "lib/bird.png");

    //     if (image == null) {
    //         image = Utilities.loadImage("lib/bird.png");
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