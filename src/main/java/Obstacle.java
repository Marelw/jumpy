import java.awt.*;

public class Obstacle {
    public String direction;
    public Rectangle rectObstacle;
    public int speed = 3;

    public Obstacle(String direction, int pos, int x) {
        this.direction = direction;
        int y = 0;
        int height = 400;
        int width = 66;

        if (direction.equals("floor")) {
            y = pos + 80;
        }
        else {
            height = pos - 80;
        }

        this.rectObstacle = new Rectangle(x, y, width, height);
    }

    public void reset(int pos) {
        int height = 400;
        int width = 66;
        int x = GameConstraints.PANEL_WIDTH + 2;
        int y = 0;

        if (direction.equals("floor")) {
            rectObstacle.y = pos + 80;
        }
        else {
            rectObstacle.height = pos - 80;
        }

        rectObstacle.x = x;
    }

    public boolean isOffScreen() {
        return (rectObstacle.x < -66);
    }

    public void update() {
        rectObstacle.x -= speed;
    }
}
