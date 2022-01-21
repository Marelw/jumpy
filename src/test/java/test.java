import java.io.BufferedReader;
import java.io.FileReader;

public class test {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("lib/Background.png"))) {
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
