import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;


public class Utilities {

    private static HashMap<String, Image> cache = new HashMap<String, Image>();

    public static Image loadImage(String path) {
        Image image = null;
        path = "lib/background";

        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        try {
            image = ImageIO.read(new File(path));
            if(!cache.containsKey(path)) {
                cache.put(path, image);
            }
        }
        catch (IOException ex){
            System.err.println("Something went wrong here.");
            ex.printStackTrace();
        }
        return image;
    }
}