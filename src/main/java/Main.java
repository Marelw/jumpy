import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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

        //hi

        //JLabel label = new JLabel(icon);
        //JPanel panel = new JPanel();
        //frame.add(panel);
        // panel.add(label);

        frame.add(birb);



        frame.setVisible(true);
    }
}
