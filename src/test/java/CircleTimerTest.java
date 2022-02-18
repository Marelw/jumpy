import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CircleTimerTest extends JPanel {
    private Color color = Color.RED;                 // global color object
    static JButton button = new JButton("Change");
    private Timer timer = null;

    public CircleTimerTest(){

        timer = new Timer(4000, new ActionListener(){      // Timer 4 seconds
            public void actionPerformed(ActionEvent e) {
                color = Color.RED;                         // after 4 seconds change back to red
                repaint();
            }
        });


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                color = Color.GREEN;        // change to green
                repaint();                  // repaint
                timer.start();              // start timer
            }
        });
    }

    // Fönstret
    private static void createAndShowGui() {
        JFrame frame = new JFrame();
        frame.add(new CircleTimerTest(), BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    // Målar Cirkeln
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
    }

    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            // Timertask?
            public void run() {
                createAndShowGui();
            }
        });
    }
}
