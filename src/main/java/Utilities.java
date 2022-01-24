import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;


public class Utilities {

    private static HashMap<String, Image> stack = new HashMap<String, Image>();

    public static Image loadImage(String path) {
        Image image = null;

        if (stack.containsKey(path)) {
            return stack.get(path);
        }

        try {
            image = ImageIO.read(new File(path));
            if(!stack.containsKey(path)) {
                stack.put(path, image);
            }
        }
        catch (IOException ex){
            System.err.println("Something went wrong here.");
            ex.printStackTrace();
        }
        return image;
    }
}