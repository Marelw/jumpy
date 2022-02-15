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
            this.birbImageSprite = ImageIO.read(new File("lib/jumpy-face.png"));
            this.setBackground(Color.BLUE);
            //this.birbImageSpriteCount = 0;
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }

        this.setBounds(10,10,WIDTH,HEIGHT);
        this.setOpaque(true);

        this.birbPic = new Rectangle(140,200, 65, 55 );
    }

    public void Pipe()  {
        // this.pipes = new ArrayList<>();
        Thread pipeAnimation = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    }catch (Exception e) {
                    }
                }
            }
        });
        pipeAnimation.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawBirb(g2d);
        drawPipes(g2d);
    }

    /**
     * Draws in both foreground and background, sets how many pixels they cover.
     * @param g
     */
    private void drawBirb(Graphics2D g){
        final Dimension d = this.getSize();
        //int offset = 46 * birbImageSpriteCount;

        g.drawImage(birbImageSprite, birbPic.x, birbPic.y, birbPic.x + birbPic.width,
                birbPic.y + birbPic.height, 0, 0, birbPic.width,birbPic.height, null);
    }

    private void drawPipes(Graphics2D g) {
        int lastPos = 550;
        Graphics2D g2d = (Graphics2D) g;

        int h = getHeight();
        int w = getWidth();

        // Målar pipes
        int pipeHeight = 155;
        int pipeWidht = 20;
        int pipeSpeed = 1;


        int x = lastPos + pipeSpeed;

        if (x > w + pipeWidht) {
            x = -pipeWidht;
        }

        //for (Pipes pipes : pipe) {
        // g.setColor(Color.GREEN);
        // g.fillRect(pipes.bounds.x, pipes.bounds.y, pipes.bounds.width, pipes.bounds.height);
        // }

        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, h/2 + pipeHeight, pipeWidht, pipeHeight);

        lastPos = x;
    }

    private void drawBackground(Graphics2D g){
        g.drawImage(background, 0, 0, 650, 700, 0, 0, 650,700, null);
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