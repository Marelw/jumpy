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
import java.util.ArrayList;


public class GameConstraints extends JPanel implements ActionListener, KeyListener {


    Timer timer;

    // Panel size
    public static final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 525;

    /**
     * Bird constants & attributes
     */
    private BufferedImage birdImageSprite;
    int birdVelocity = 0;
    double newVelocity = 4.0;
    double posY = 200;
    int posX = 200;


    private transient FrameUpdater updater;
    private java.util.List<Obstacle> obstacles;

    public static final int PIPE_DELAY = 100;
    private int pauseDelay; // om vi ska kunna pausa
    private int restartDelay; // för restart
    private int pipeDelay;




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
        this.obstacles = new ArrayList<>();
        this.updater = new FrameUpdater(this, 60);
        this.updater.setDaemon(true); // it should not keep the app running
        this.updater.start();

        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(10, this);
        timer.start();
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
        for (Obstacle obstacle : obstacles) {
            g2D.setColor(Color.MAGENTA);
            g2D.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
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

    public void update(int time) {
        addObstacle();

    }

    private void addObstacle() {

        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Obstacle ceilingObstecle = null;
            Obstacle floorObstecle = null;

            // Look for pipes off the screen
            for (Obstacle obstacle : obstacles) {
                if (obstacle.x - obstacle.width < 0) {
                    if (ceilingObstecle == null) {
                        ceilingObstecle = obstacle;
                    } else if (floorObstecle == null) {
                        floorObstecle = obstacle;
                        break;
                    }
                }
            }

            if (ceilingObstecle == null) {
                Obstacle obstacle = new Obstacle("cieling");
                obstacles.add(obstacle);
                ceilingObstecle = obstacle;
            } else {
                ceilingObstecle.reset();
            }

            if (floorObstecle == null) {
                Obstacle obstacle = new Obstacle("floor");
                obstacles.add(obstacle);
                floorObstecle = obstacle;
            } else {
                floorObstecle.reset();
            }

            ceilingObstecle.y = floorObstecle.y + floorObstecle.height + 175;
        }

        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            posY -= 80.0; // ändrar hur högt man hoppar
            posY = Math.max(0, posY); // kan ej ta dig genom taket
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}