import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    //changing these values will change the size of the game, while still remaining functional
    //within the size limit specified.
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;

    //Creates a Square object Array - Gör om den till list!
    Square[] squareArray = new Square[20];


    public Game() {

        //initializes square objects
        for (int i = 0; i < squareArray.length; i++)
            squareArray[i] = new Square();
    }

    public void paint(Graphics graphics) {

        //makes background black
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //paints square objects to the screen
        for (Square aSquareArray : squareArray) {
            aSquareArray.paint(graphics);
        }
    }

    public void update() {

        //calls the Square class update method on the square objects
        for (Square aSquareArray : squareArray) aSquareArray.update();
    }

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Raining Multiple Squares");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        while (true) {
            game.update();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
  