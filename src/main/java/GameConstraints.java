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
    // int birdVelocity = 0;
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

    private final Birb birb = new Birb();

    private enum STATE {
        MENU,
        GAME
    };

    private STATE State = STATE.MENU;


    /**
     * Loads in the images from lib catalog in a try-catch.
     * If that fails it will print an exception.
     */
    public GameConstraints() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.ORANGE);
        /*
        this.gameOver = false;
        try {
            this.birdImageSprite = ImageIO.read(new File("lib/hampus.png"));
            //this.birdImageSpriteCount = 0;
        } catch (IOException ex) {
           // System.out.println(ex + " Unable to load image");
        }

         */

        if (State == STATE.MENU){
            GameMenu();
        }


        this.obstacles = new ArrayList<>();
        this.updater = new FrameUpdater(this, 60);
        this.updater.setDaemon(true); // it should not keep the app running
        if (State == STATE.GAME){
            this.updater.start();
        }

        // All key events
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);


        Timer timer = new Timer(2, e -> {
            long time = System.nanoTime();
            birb.tick(time);
            repaint();
        });
        timer.start();
    }


    private void GameMenu() {
        JButton start = new JButton("Start");
        this.add(start);
        start.setMnemonic(KeyEvent.VK_S);
        start.setActionCommand("Start");
        start.addActionListener(this);
        start.setFocusable(false);


    }


    // Will draw
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Dimension d = this.getSize();


        Graphics2D g2D = (Graphics2D) g;
        //drawBird(g2D);

        // Ritar över allt man gör i drapPipes. Anropar aldrig metoden som lägger hinder!!!
        //g.fillRect(0, 0, d.width, d.height);
        birb.paint(g2D);
        drawPipes(g2D);

    }

    /**
     * Draws in both foreground and background, sets how many pixels they cover.
     *
     */
    /*private void drawBird(Graphics2D g2D) {

        if (posY < (PANEL_HEIGHT - birdImageSprite.getHeight(null)) || posY >= -100) {
            posY += birdVelocity;
            // cant go above Panel/frame
        }
        g2D.drawImage(birdImageSprite, posX, (int) posY, null);
    }
*/


    private void drawPipes(Graphics2D g2D) {
        for (Obstacle obstacle : obstacles) {
            g2D.setColor(Color.MAGENTA);
            g2D.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Start".equals(e.getActionCommand())){
            State = STATE.GAME;
            timer.start();
            this.updater.start();
            ((JButton)e.getSource()).setVisible(false);
        }

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
        if (e.getButton() == MouseEvent.BUTTON1){
            final long time = System.nanoTime();
            birb.jump(time);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {



        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        final int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_SPACE) {
            final long time = System.nanoTime();
            birb.jump(time);
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