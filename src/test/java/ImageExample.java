import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageExample {
    public static void main(String[] args) {
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("lib/background.png").getImage());
        JLabel label = new JLabel(backgroundIcon);
        label.setSize(400, 500);

        JButton startButton = new JButton("Start");
        startButton.setBounds(50, 50, 100, 50);

        label.add(startButton);

        

        JFrame frame = new JFrame("Flappy Birb Test 2");
        frame.add(label);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
