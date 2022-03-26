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
    3. Lägg till timer --V
    4. Lägg rektangel i en list (för random generator)
 */

public class PipeGenerator extends JPanel{
    // Vet ej
    private static final long serialVersionUID = 1L;

    // Vet inte än
    private static final int PREF_W = 900;
    private static final int PREF_H = 500;

    // Delayen på hur långt tid det ska ta att generera rektangeln
    private static final int TIMER_DELAY = 20;

    // Genereringen på rektangelns position
    private Timer timer = null;

    private List<PipeCon> allPipes;

    // gör att pipes rör sig
    public PipeGenerator() {
        allPipes = createPipeList();

        new Timer(TIMER_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actEvt) {
                for (PipeCon p : allPipes) {
                    if (p.rectX < PREF_W && p.rectY < PREF_H
                            && p.rect2X < PREF_W && p.rect2Y < PREF_H ) {

                        p.rectX--;
                        p.rect2X--;
                    }
                }
                repaint();
            }
        }).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    // Ritar rektangeln
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PipeCon pipeCon: allPipes) {
            pipeCon.drawPipe(g);
        }
    }

    private List<PipeCon> createPipeList() {
        List<PipeCon> list  = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int randXLoc = random.nextInt(PREF_W);
            int randomDelayedStart = random.nextInt(100);
            list.add(new PipeCon(randXLoc, randomDelayedStart));
        }
        return list;
    }

}

class PipeCon {
    // Rektangel 1 position
    public int rectX = 400;
    public int rectY = 300;

    //Rektangel 2 position
    public int rect2X = 400;
    public int rect2Y = 0;

    int randXLoc;
    int randomDelayStart;

    public PipeCon(int randXLoc, int randomDelayStart){
        this.randXLoc = randXLoc;
        this.randomDelayStart = randomDelayStart;
    }


    public void drawPipe(Graphics g) {
         g.setColor(Color.red);
         g.drawRect(rectX, rectY, 50, 100);
         g.fillRect(rectX, rectY, 50, 100);

         g.setColor(Color.blue);
         g.drawRect(rect2X, rect2Y, 50, 100);
         g.fillRect(rect2X, rect2Y, 50, 100);
    }

}

