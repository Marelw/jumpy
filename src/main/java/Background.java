import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Background {
    public int height;
    public int width;
    public Image image;
    public AffineTransform transform;

    public Background(){
    }

    public void Background(int height, int width, String imagePath){
        Toolkit.getDefaultToolkit().sync();
        this.height = height;
        this.width = width;
        this.image = Utilities.loadImage(imagePath);
        
    }
    
}
