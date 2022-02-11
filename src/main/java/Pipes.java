import java.awt.*;

public class Pipes {
    public final int created;
    public final Rectangle bounds;

    public Pipes (int created, int x, int y){
        this.created = created;
        this.bounds = new Rectangle(x, y, 10, 10);
    }
}
