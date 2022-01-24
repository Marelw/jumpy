import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Rendering {
    public int xPos;
    public int yPos;
    public Image imageRender;
    public AffineTransform transform;

    public Rendering(int xPos, int yPos, String imagePath) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.imageRender = Utilities.loadImage(imagePath);
        Toolkit.getDefaultToolkit().sync();
    }
}
