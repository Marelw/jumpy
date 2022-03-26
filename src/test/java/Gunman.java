import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gunman extends JComponent {

    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 900;
    private static final int PREF_H = 700;
    private static final int TIMER_DELAY = 30;
    public int rectX = 10;
    public int rectY = 10;
    public int width = 8;
    public int height = 10;

    public Gunman() {
        new Timer(TIMER_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actEvt) {
                if (rectX < PREF_W && rectY < PREF_H) {
                    rectX++;
                    rectY++;
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

    // rektangeln
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.drawRect(rectX, rectY, width, height);
        g.fillRect(rectX, rectY, width, height);
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

    public void setRectY(int rectY) {
        this.rectY = rectY;
    }

    // Fönstrest
    private static void createAndShowGui() {
        Gunman mainPanel = new Gunman();

        JFrame frame = new JFrame("Gunman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

}