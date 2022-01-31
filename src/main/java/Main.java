
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        JFrame frame = new JFrame("Flappy Birb");
        frame.setBounds(100, 100, 500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ImageIcon icon = new ImageIcon(new ImageIcon("lib/background.png").getImage());
        Birb birb = new Birb();
        //frame.getContentPane().add(birb.getBirb());

        //JLabel label = new JLabel(icon);
        //JPanel panel = new JPanel();
        //frame.add(panel);
       // panel.add(label);

        frame.add(birb);



        frame.setVisible(true);

    }
}
