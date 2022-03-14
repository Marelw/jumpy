import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Birb implements Serializable {
    private static final long serialVersionUID = 126058262246325L;

    private static final double GRAVITY = 90;
    private static final double TIME_SCALE = 40L;
    private static final double NANOSECOND = 1_000_000_000L;

    private final Rectangle box = new Rectangle(220, 180, 40, 40);
    static Rectangle birbRect;
    private double velocity = 0;
    private long jumpStart = 0;

    // this is just here to keep it still until first jump
    private boolean started = false;

    int posY = 200;
    int posX = 200;

    private BufferedImage birdImageSprite;

    int birdVelocity = 0;

    private int birbWidth;
    private int birbHeight;

    public Birb() {
        try {
            birdImageSprite = ImageIO.read(new File("lib/hampus.png"));
            //this.birdImageSpriteCount = 0;
        } catch (IOException ex) {
            // System.out.println(ex + " Unable to load image");
        }
        this.birbWidth = birdImageSprite.getWidth();
        this.birbHeight = birdImageSprite.getHeight();
        this.birbRect = new Rectangle(posX, posY, birbWidth, birbHeight);
    }

    public void jump(long time) {
        started = true;

        // using this if we can only jump on the way down
        if (velocity <= 0) {
            jumpStart = time;
            velocity = 100;
        }
    }

    // Rending the jump
    public void tick(long time) {
        if (!started) {
            return;
        }

        double deltaTime = ((time - jumpStart) / NANOSECOND) / TIME_SCALE;
        //System.out.println(deltaTime);
        birbRect.y -= velocity * deltaTime;
        velocity -= GRAVITY * deltaTime;
    }

    public Rectangle getBirbRect() {
        return birbRect;
    }

    public int getBirbWidth() {
        return birbWidth;
    }

    public int getBirbHeight() {
        return birbHeight;
    }

    public void paint(Graphics2D g2D) {
        // Läs bilden --v
        // Ange bredd och höjd på bilden (.get) --v
        // Skapa rektangel --v
        // använd drawImage (för att få bilden och rektangeln att synka). Lägg if-sats --v
        // Använd intersect

        if (birdImageSprite != null) {
            g2D.drawImage(birdImageSprite, (int) birbRect.getX(), (int) birbRect.getY(), null);
            System.out.println(getBirbRect());
        } else {
            g2D.setColor(Color.MAGENTA);
            g2D.fillRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
        }
    }


}