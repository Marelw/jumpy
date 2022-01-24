import javax.swing.*;

public class ButtonExample {
    public static void button() {
        JFrame f = new JFrame();
        JButton b = new JButton(new ImageIcon("lib/bluejumpy.png"));

        b.setBounds(100, 100, 100, 40);
        f.add(b);
        f.setSize(400, 500);
        f.setLayout(null); //gör så att man verkligen får storlek på knappen.
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
    
    public static void main(String[] args) {
        button();
    }
}
