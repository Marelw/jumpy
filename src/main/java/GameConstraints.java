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
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameConstraints extends JPanel implements ActionListener, KeyListener {

    private static final double OBSTICLE_PIXELS_PER_MS = 0.12;
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
    private int pauseDelay;
    private int restartDelay;
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
            g2D.setColor(Color.PINK);
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

        //final Dimension d = this.getSize();

/*        if (obstacles.isEmpty() || obstacles.size() < 10) {
            for (int i = obstacles.size(); i < 10; ++i) {
                addObsticle();
            }
        }*/
        addObsticle();

        // final java.util.List<Obstacle> toRemove = new ArrayList<>();

/*        for (Obstacle obstacle : obstacles) {
             movement is based on elapsed time to make it smoother and
             more consistent over different computers
            int timeElapsed = time - 10;
            obstacle.x = (int) (500 - (timeElapsed * OBSTICLE_PIXELS_PER_MS));
            if (obstacle.x + obstacle.width < 0) {
                // we add to another list and remove later
                // to avoid concurrent modification in a for-each loop
                // toRemove.add(obstacle);
                //obstacle.invalid = true;
            }

        }
        obstacles = obstacles.stream().filter(f -> !f.invalid).collect(Collectors.toList());*/
    }

    private void addObsticle() { //final int time, final int height, boolean randomX
/*        int newTime = time;
        if (randomX) {
            // make sure they start randomly somewhere on the screen
            // by adjusting the create time, making it seem like they
            // have traveled on the screen for some time already
            final int MIN_PIXELS_FROM_LEFT = 600;
            final int MS_TO_TRAVEL_MIN_PIXELS = (int)(MIN_PIXELS_FROM_LEFT / OBSTICLE_PIXELS_PER_MS);
            newTime = time - ThreadLocalRandom.current().nextInt(MS_TO_TRAVEL_MIN_PIXELS);
        }

        final int FAR_OFFSCREEN = 10000;
        int y = ThreadLocalRandom.current().nextInt(0, PANEL_HEIGHT);
        obstacles.add(new Obstacle(newTime, FAR_OFFSCREEN, y));*/

        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Obstacle northPipe = null;
            Obstacle southPipe = null;

            // Look for pipes off the screen
            for (Obstacle obstacle : obstacles) {
                if (obstacle.x - obstacle.width < 0) {
                    if (northPipe == null) {
                        northPipe = obstacle;
                    } else if (southPipe == null) {
                        southPipe = obstacle;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Obstacle obstacle = new Obstacle("north");
                obstacles.add(obstacle);
                northPipe = obstacle;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Obstacle obstacle = new Obstacle("south");
                obstacles.add(obstacle);
                southPipe = obstacle;
            } else {
                southPipe.reset();
            }

            northPipe.y = southPipe.y + southPipe.height + 175;
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
            posY -= 100.0; // ändrar hur högt man hoppar
            posY = Math.max(0, posY); // kan ej ta dig genom taket
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}