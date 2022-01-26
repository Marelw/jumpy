import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        ImageIcon icon = new ImageIcon(new ImageIcon("lib/background.png").getImage());

        JLabel label = new JLabel(icon);
        JFrame frame = new JFrame("Flappy Birb");
        JPanel panel = new JPanel();
        
        JButton button = new JButton("Start");
        frame.add(panel);
        
        panel.add(button);
        panel.add(label);
        
        frame.setVisible(true);
        
        // Window size and location
        frame.setSize(400, 500);
        frame.setLocation(100,100);
        frame.setResizable(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
