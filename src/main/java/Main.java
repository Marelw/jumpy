import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JFrame;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        JFrame frame = new JFrame("Jumpy Birb");
        frame.setSize(650, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        GameConstraints gC = new GameConstraints();
        frame.add(gC);



        frame.setVisible(true);
    }
}
