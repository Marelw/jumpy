import java.awt.Rectangle;

public class Obstacle {
    public final int created;
    public final Rectangle bounds;
    public boolean invalid = false;
    public Obstacle(int created, int x, int y) {
        this.created = created;
        this.bounds = new Rectangle(400, 400, 35, 200);
    }
}
