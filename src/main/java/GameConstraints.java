import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameConstraints extends JPanel {

    public final int HEIGHT = 400;
    public final int WIDTH = 700;
    private Rectangle birbPic;
    private BufferedImage birbImageSprite;
    private BufferedImage background;
    private BufferedImage foreground;
    private List<Pipe> pipes;
    private Timer timer = null;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawBirb(g2d);
        drawPipes(g2d);

    }

    private void drawBirb(Graphics2D g){
        final Dimension d = this.getSize();
        //int offset = 46 * birbImageSpriteCount;

        g.drawImage(birbImageSprite, birbPic.x, birbPic.y, birbPic.x + birbPic.width,
                birbPic.y + birbPic.height, 0, 0, birbPic.width,birbPic.height, null);
    }

    private void drawPipes(Graphics2D g) {
        final Dimension d  = this.getSize();


    }

    private void drawBackground(Graphics2D g){
        g.drawImage(background, 0, 0, 650, 700, 0, 0, 650,700, null);
    }

}