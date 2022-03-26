import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Square extends JPanel {

    private int squareXLocation;
    private int squareSize;
    private int squareYLocation = -squareSize;
    private int fallSpeed = 1;
    Random rand = new Random();

    /*
    //creates a random value inside the window and stores it in squareXLocation
    */
    public int generateRandomXLocation(){
        return squareXLocation = rand.nextInt(Game.WINDOW_WIDTH - squareSize);
    }

    /*
    //creates a random value between 1-50 and stores it in squareWidth
    */
    public int generateRandomSquareSize(){
        return squareSize = rand.nextInt(50);
    }

    /*
    //creates a random value that is not zero and stores it in fallSpeed(so squares do not get stuck at 0 speed)
    */
    public int generateRandomFallSpeed(){
        return fallSpeed = rand.ints(1, 1, 10).findFirst().getAsInt();
    }

    /*
    //paints the square with the variables generated in the random methods
    */
    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(squareXLocation,squareYLocation,squareSize,squareSize);
    }

    /*
    //sets the squareWidth and square fallSpeed to a random value for every square created
    */
    public Square(){
        generateRandomXLocation();
        generateRandomSquareSize();
        generateRandomFallSpeed();
    }

    public void update(){

        //changes the squares xLocation and fallSpeed if the created square reaches the bottom of the screen
        if(squareYLocation >= Game.WINDOW_HEIGHT){
            generateRandomXLocation();
            generateRandomFallSpeed();
            generateRandomSquareSize();
            squareYLocation = -squareSize;
        }

        //moves the square down if the square is inside the window
        if(squareYLocation <= Game.WINDOW_HEIGHT){
            squareYLocation += fallSpeed;
        }
    }
}