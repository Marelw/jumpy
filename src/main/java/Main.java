import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main{
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("lib/background.png").getImage());
       
        JLabel label = new JLabel(backgroundIcon);

        JFrame frame = new JFrame("Flappy Birb");
        JPanel panel = new JPanel();
        
        JButton button = new JButton("Dude");

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
