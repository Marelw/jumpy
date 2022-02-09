import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JFrame;


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
        GameConstraints gC = new GameConstraints();
        //frame.getContentPane().add(birb.getBirb());

        //hi

        //JLabel label = new JLabel(icon);
        //JPanel panel = new JPanel();
        //frame.add(panel);
        // panel.add(label);
        //useless comment for commit
        frame.add(gC);



        frame.setVisible(true);
    }
}
