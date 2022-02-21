public class Obstacle {
    //public final int created;
    //public final Rectangle bounds;
    public boolean invalid = false;

    public String Direction;
    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public Obstacle(String direction) {
        this.Direction = direction;
        reset();
    }
    public void reset() {
        width = 66;
        height = 400;
        x = GameConstraints.PANEL_WIDTH + 2;

        if (Direction.equals("south")) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }
}
