
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        ImageIcon icon = new ImageIcon(new ImageIcon("lib/background.png").getImage());
        
        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("Flappy Birb");
        JPanel panel = new JPanel();
        
        ImageIcon image = new ImageIcon("lib/bluejumpy.png");
        Birb birb = new Birb(image, 200, 100);
        frame.getContentPane().add(birb.getBirb());

        frame.add(panel);
        panel.add(label);
        frame.setVisible(true);
        
        // Window size and location
        //frame.setSize(400, 500);
        //frame.setLocation(100,100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setBounds(100, 100, 800, 500);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
