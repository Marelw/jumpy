import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
    1. Skapa JFrame --v
    2. Rita rektangel --v
    3. Lägg till timer
    4. Lägg rektangel i en list
 */

public class PipeTest extends JPanel{
    // Genererar random?
    private static final int D_HEIGHT = 500;
    private static final int D_WIDTH = 400;
    
    // Vet ej
    private static final long serialVersionUID = 1L;

    // Vet inte än
    private static final int PREF_W = 900;
    private static final int PREF_H = 500;

    // Delayen på hur långt tid det ska ta att generera rektangeln
    private static final int TIMER_DELAY = 20;

    //Rektangelns position (röd)
    public int rectX = 400;
    public int rectY = 300;

    //Rektangel 2 position
    public int rect2X = 400;
    public int rect2Y = 0;

    //Rektangelns mått
    public int widthX = 50;
    public int heightY = 200;

    //Rektangel 2 mått
    public int width2X = 50;
    public int height2Y = 200;

    // Genereringen på rektangelns position
    private Timer timer = null;

    // gör att pipes rör sig
    public PipeTest() {
        new Timer(TIMER_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actEvt) {
                if (rectX < PREF_W && rectY < PREF_H
                    && rect2X < PREF_W && rect2Y < PREF_H ) {

                    rectX--;
                    rect2X--;

                    repaint();
                } else {
                    ((Timer)actEvt.getSource()).stop();
                }
            }
        }).start();
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    // Ritar rektangeln
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.drawRect(rectX, rectY, widthX, heightY);
        g.fillRect(rectX, rectY, widthX, heightY);

        g.setColor(Color.blue);
        g.drawRect(rect2X, rect2Y, width2X, height2Y);
        g.fillRect(rect2X, rect2Y, width2X, height2Y);
    }

    public int getRectX() {
        return rectX;
    }

    public void setRectX(int rectX) {
        this.rectX = rectX;
    }

    public int getRectY() {
        return rectY;
    }



}