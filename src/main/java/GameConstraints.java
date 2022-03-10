import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GameConstraints extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Timer timer;

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

    public boolean gameOver;


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

        try {
            birdImageSprite = ImageIO.read(new File("lib/hampus.png"));
            //this.birdImageSpriteCount = 0;
        } catch (IOException ex) {
            // System.out.println(ex + " Unable to load image");
        }

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.ORANGE);

        if (State == STATE.MENU){
            GameMenu();
        }

        this.obstacles = new ArrayList<>();

        // All key events
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);


        this.timer = new Timer(2, e -> {
            long time = System.nanoTime();
            birb.tick(time);
            repaint();
            // System.out.println("tick");
        });
    }


    private void GameMenu() {
        JButton start = new JButton("Start");
        this.add(start);
        start.setMnemonic(KeyEvent.VK_S);
        start.setActionCommand("Start");
        start.addActionListener(this);
        start.setFocusable(false);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Dimension d = this.getSize();

        Graphics2D g2D = (Graphics2D) g;

        // Ritar över allt man gör i drapPipes. Anropar aldrig metoden som lägger hinder!!!
        birb.paint(g2D);
        drawPipes(g2D);

    }

    private void drawPipes(Graphics2D g2D) {
        for (Obstacle obstacle : obstacles) {
            g2D.setColor(Color.MAGENTA);
            g2D.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
        update(10); // Updating pipes and makes them visible on screen.
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Start".equals(e.getActionCommand())){
            State = STATE.GAME;
            timer.start();
            ((JButton)e.getSource()).setVisible(false);
        }
        repaint();
    }

    public void update(int time) {
        if (gameOver) {
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

            // Skräp, måste fixa pga man använder inte posX, posY och birbImageSprite
            if (obstacle.collides(birb.posX, birb.posY, (int) birb.getBirbRect().getX(), (int) birb.getBirbRect().getY())) {
                gameOver = true;
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