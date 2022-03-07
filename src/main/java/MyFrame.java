import javax.swing.*;

public class MyFrame extends JFrame {

    GameConstraints gameConstraints;

    MyFrame() {
        gameConstraints = new GameConstraints();

        this.setAlwaysOnTop(true);
        this.setAutoRequestFocus(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gameConstraints);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
