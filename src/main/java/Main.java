<<<<<<<<< Temporary merge branch 1
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JFrame;


=========
>>>>>>>>> Temporary merge branch 2
public class Main {
    public static void main(String[] args) {

<<<<<<<<< Temporary merge branch 1
    private static void GameDisplay() {
        JFrame frame = new JFrame("Flappy Birb");
        frame.setBounds(100, 100, 500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ImageIcon icon = new ImageIcon(new ImageIcon("lib/background.png").getImage());
        GameConstraints gC = new GameConstraints();
        //frame.getContentPane().add(birb.getBirb());

        //JLabel label = new JLabel(icon);
        //JPanel panel = new JPanel();
        //frame.add(panel);
        // panel.add(label);
        //useless comment for commit
        frame.add(gC);
        frame.setVisible(true);
=========
        new MyFrame();
>>>>>>>>> Temporary merge branch 2
    }
}
