import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        
        f.setTitle("Flappy Birb");
        f.setVisible(true);

        // Window size and location
        f.setSize(400, 500);
        f.setLocation(100,100);
        f.setResizable(true);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
