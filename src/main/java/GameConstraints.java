import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class GameConstraints extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Timer timer;

    // Panel size
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 525;


    public static boolean gameOver;
    public int score;
    public String scoreText = String.valueOf(score);

    JLabel scorelabel = new JLabel("Current score: " + scoreText);


    private java.util.List<Obstacle> obstacles;

    public static final int PIPE_DELAY = 100;
    private int pauseDelay; // om vi ska kunna pausa
    private int restartDelay; // för restart
    private int pipeDelay;

    private final Birb birb = new Birb();
    private BufferedImage birdImageSprite;


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

        scorelabel.setFont(new Font("Arial", Font.BOLD, 35));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.ORANGE);
        scorelabel.setBounds(225, 0, 50, 30 );
        scorelabel.setVisible(true);


        if (State == STATE.MENU){
            GameMenu();
        }

        this.add(scorelabel);

        this.obstacles = new ArrayList<>();

        // All key events
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);


        this.timer = new Timer(16, e -> {
            long time = System.nanoTime();
            birb.tick(time);
            update(time);
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
            g2D.fillRect((int) obstacle.rectObstacle.getX(), (int) obstacle.rectObstacle.getY(), (int) obstacle.rectObstacle.getWidth(), (int) obstacle.rectObstacle.getHeight());
        }
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

    public void update(long time) {
        if (gameOver) {
            timer.stop(); // fungerar ej
            return;
        }

        moveObstacles();
        checkForCollisions();
        addObstacles();
    }

    private void moveObstacles() {
        for (Obstacle o : obstacles) {
            o.update();
        }
    }

    private void addObstacles() {
        if (obstacles.isEmpty()) {
            int pos = 100 + ThreadLocalRandom.current().nextInt(200);
            int pos2 = pos + ThreadLocalRandom.current().nextInt(-50, 50);

            obstacles.add(new Obstacle("ceiling", pos, GameConstraints.PANEL_WIDTH + 2));
            obstacles.add(new Obstacle("floor", pos, GameConstraints.PANEL_WIDTH + 2));

            obstacles.add(new Obstacle("ceiling", pos2, GameConstraints.PANEL_WIDTH + 300));
            obstacles.add(new Obstacle("floor", pos2, GameConstraints.PANEL_WIDTH + 300));

            return;
        }

        int pos = 100 + ThreadLocalRandom.current().nextInt(200);
        for(Obstacle obstacle : obstacles) {
            if (obstacle.isOffScreen()) {
                obstacle.reset(pos);
            }

        }
    }

    private boolean checkForCollisions() {
        for (Obstacle obstacle : obstacles) {

            if(obstacle.rectObstacle.intersects(Birb.birbRect)) {
                gameOver = true;
                return true;
            }
            else if((int) obstacle.rectObstacle.getX() == (int)birb.birbRect.getX() && obstacle.direction.equalsIgnoreCase("floor") ){
                score++;
                scoreText = String.valueOf(score);
                scorelabel.setText("Current score: " + scoreText);
            }
        }

        return false;
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