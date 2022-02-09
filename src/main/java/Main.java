import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        JFrame frame = new JFrame("Jumpy Birb");
        frame.setBounds(100, 100, 500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameConstraints gC = new GameConstraints();
        frame.add(gC);



        frame.setVisible(true);
    }
}
