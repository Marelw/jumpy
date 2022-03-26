import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameConstraintsTest extends JPanel {

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

    public GameConstraintsTest() {
        super();
        try {
            this.birbImageSprite = ImageIO.read(new File("lib/jumpy-face.png")); //Fågeln
            this.setBackground(Color.BLUE); //Bakgrunden
            //this.birbImageSpriteCount = 0;
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }

        this.setBounds(10, 10, WIDTH, HEIGHT);
        this.setOpaque(true);

        this.birbPic = new Rectangle(140, 200, 65, 55);
    }

    // Målar fågeln
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawBirb(g2d);
    }

    // Lägger ut och målar fågeln
    private void drawBirb(Graphics2D g) {
        final Dimension d = this.getSize();
        //int offset = 46 * birbImageSpriteCount;

        g.drawImage(birbImageSprite, birbPic.x, birbPic.y, birbPic.x + birbPic.width,
                birbPic.y + birbPic.height, 0, 0, birbPic.width, birbPic.height, null);
    }

    private void drawBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, 650, 700, 0, 0, 650, 700, null);

    }

    // Måla pipes

}
