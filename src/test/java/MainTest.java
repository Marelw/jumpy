import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainTest {
    public static void main(String[] args) {
        GameDisplay();
    }

    private static void GameDisplay() {
        JFrame frame = new JFrame("Flappy Birb");
        frame.setBounds(100, 100, 500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        PipeGenerator pipeGenerator = new PipeGenerator();
        frame.add(pipeGenerator);
        frame.setVisible(true);
    }
}
