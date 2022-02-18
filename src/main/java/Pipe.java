import java.awt.*;

public class Pipe{
    public final int created;
    public final Rectangle bounds;

    public Pipe (int created, int x, int y){
        this.created = created;
        this.bounds = new Rectangle(x, y, 10, 10);
    }
}

