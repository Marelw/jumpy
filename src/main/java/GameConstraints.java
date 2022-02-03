import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameConstraints extends JPanel {

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
    private BufferedImage foreground;
    //private int birbImageSpriteCount;
    //private Keyboard keyboard;

    /**
     * Loads in the images from lib catalog in a try-catch.
     * If that fails it will print an exception.
     */
    public GameConstraints() {
        super();
        try {
            this.birbImageSprite = ImageIO.read(new File("lib/bird.png"));
            this.background = ImageIO.read(new File("lib/background.png"));
            this.foreground = ImageIO.read(new File("lib/foreground.png"));
            //this.birbImageSpriteCount = 0;
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }

        this.setBounds(10,10,WIDTH,HEIGHT);
        this.setOpaque(true);

        this.birbPic = new Rectangle(140,200, 65, 50 );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBirb(g2d);
    }

    /**
     * Draws in both foreground and background, sets how many pixels they cover.
     * @param g
     */
    private void drawBirb(Graphics2D g){
        final Dimension d = this.getSize();
        //int offset = 46 * birbImageSpriteCount;
        g.drawImage(foreground,0,0,500, 500, 0,0,500, 500, null );

        g.drawImage(background, 0, 0, 500, 425, 0, 0, 500,425, null);


        g.drawImage(birbImageSprite, birbPic.x, birbPic.y, birbPic.x + birbPic.width,
                birbPic.y + birbPic.height, 0, 0, birbPic.width,birbPic.height, null);
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
    // public void kill() {
    //     isAlive = false;
    // }

}