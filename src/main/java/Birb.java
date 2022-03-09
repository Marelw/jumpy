import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Birb implements Serializable {
    private static final long serialVersionUID = 126058262246325L;

    private static final double GRAVITY = 90;
    private static final double TIME_SCALE = 30L;
    private static final double NANOSECOND = 1_000_000_000L;

    private final Rectangle box = new Rectangle(220, 180, 40, 40);
    private double velocity = 0;
    private long jumpStart = 0;

    // this is just here to keep it still until first jump
    private boolean started = false;

    double posY = 200;
    int posX = 200;

    // Panel size
    public static final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 525;

    private BufferedImage birdImageSprite;

    int birdVelocity = 0;


    public void jump(long time) {
        started = true;

        // using this if we can only jump on the way down
        if (velocity <= 0) {
            jumpStart = time;
            velocity = 100;
        }
    }

    public void tick(long time) {
        if (!started) {
            return;
        }

        double deltaTime = ((time - jumpStart) / NANOSECOND) / TIME_SCALE;
        System.out.println(deltaTime);
        box.y -= velocity * deltaTime;
        velocity -= GRAVITY * deltaTime;
    }

    public void paint(Graphics2D g2D) {

    // intersect

        g2D.drawImage(birdImageSprite, posX, (int) posY, null);




        g2D.setColor(Color.MAGENTA);
        g2D.fillRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());

    }

}