import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameConstraints extends JPanel implements ActionListener, KeyListener {

    Timer timer;

    // Panel size
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 525;

    /**
     * Bird constants & attributes
     */
    private BufferedImage birdImageSprite;
    int birdVelocity = 0;
    double newVelocity = 4.0;
    double posY = 200;
    int posX = 200;



    /**
     * Loads in the images from lib catalog in a try-catch.
     * If that fails it will print an exception.
     */
    public GameConstraints() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.ORANGE);
        try {
            this.birdImageSprite = ImageIO.read(new File("lib/jumpy-face.png"));
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(1, this);
        timer.start();
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

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        drawBird(g2D);

        drawPipes(g2D);
    }

    /**
     * Draws in both foreground and background, sets how many pixels they cover.
     *
     */
    private void drawBird(Graphics2D g2D) {

        if (posY < (PANEL_HEIGHT - birdImageSprite.getHeight(null)) || posY >= -100) {
            posY += birdVelocity;
            // cant go above Panel/frame
        }
        g2D.drawImage(birdImageSprite, posX, (int) posY, null);


    }

    private void drawPipes(Graphics2D g2D) {
        int lastPos = 550;
        Graphics2D g2d = (Graphics2D) g2D;

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


    @Override
    public void actionPerformed(ActionEvent e) {

        if (posY >= PANEL_HEIGHT- birdImageSprite.getHeight(null) || posY < 0) {
            // GAME OVER, cant go under jpanel
        } else {
            posY += newVelocity;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            posY -= 100.0; // ändrar hur högt man hoppar
            posY = Math.max(0, posY); // kan ej ta dig genom taket
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}