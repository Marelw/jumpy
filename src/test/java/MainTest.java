import java.awt.*;
import javax.swing.*;

public class MainTest extends JPanel {
    public void paint(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("lib/background.png");
        g.drawImage(img1, 50, 50, this);

        Image img2 = Toolkit.getDefaultToolkit().getImage("lib/bluejumpy.png");
        g.drawImage(img2, 50, 50, this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Display multiple images");
        f.getContentPane().add(new MainTest());
        f.setSize(400, 500);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
