import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GameConstraints extends JPanel implements ActionListener, KeyListener, MouseListener {


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

    public Boolean gameOver;


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
        this.gameOver = false;
        try {
            this.birdImageSprite = ImageIO.read(new File("lib/hampus.png"));
            //this.birdImageSpriteCount = 0;
        } catch (IOException ex) {
            System.out.println("ex" + " Unable to load image");
        }
        this.obstacles = new ArrayList<>();
        this.updater = new FrameUpdater(this, 60);
        this.updater.setDaemon(true); // it should not keep the app running
        this.updater.start();

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(20, this);
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
            // cant go above Panel/frame gameOver??
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
            gameOver = true;
        } else {
            posY += newVelocity;
        }
        repaint();
    }

    public void update(int time) {
        if (gameOver) {
            updater.interrupt();
            timer.stop(); // fungerar ej
            return;
        }
        addObstacle();
        checkForCollisions();
    }

    private void addObstacle() {
        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Obstacle ceilingObstacle = null;
            Obstacle floorObstacle = null;

            // Look for pipes off the screen
            for (Obstacle obstacle : obstacles) {
                if (obstacle.x - obstacle.width < 0) {
                    if (ceilingObstacle == null) {
                        ceilingObstacle = obstacle;
                    } else if (floorObstacle == null) {
                        floorObstacle = obstacle;
                        break;
                    }
                }
            }

            if (ceilingObstacle == null) {
                Obstacle obstacle = new Obstacle("cieling");
                obstacles.add(obstacle);
                ceilingObstacle = obstacle;
            } else {
                ceilingObstacle.reset();
            }

            if (floorObstacle == null) {
                Obstacle obstacle = new Obstacle("floor");
                obstacles.add(obstacle);
                floorObstacle = obstacle;
            } else {
                floorObstacle.reset();
            }

            ceilingObstacle.y = floorObstacle.y + floorObstacle.height + 175;
        }

        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

    }
    private void checkForCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collides(posX, (int) posY, birdImageSprite.getWidth(), birdImageSprite.getHeight())) {
                gameOver = true;
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        birbJump();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            birbJump();
        }
    }

    private void birbJump() {

        if(!gameOver) {
        posY -= 70.5; // ändrar hur högt man hoppar
        posY = Math.max(0, posY); // kan ej ta dig genom taket
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}