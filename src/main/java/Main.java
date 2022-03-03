import java.util.Scanner;

public class Main {
    public static String playerName;
    public static void main(String[] args)  {
        try {
            gameMenu();
        } catch (InterruptedException ex){
            System.err.println("Error loading the gamemenu");
            ex.printStackTrace();
        }

    }

    private static void gameMenu() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Before we begin i would like to know your name");
        System.out.println("What should i call you?");
        playerName = scan.nextLine();
        System.out.println("Okay " + playerName + ", lets see what you got!");
        System.out.println("Enjoy your game!");
        Thread.sleep(2000);
        new MyFrame();
    }
}
