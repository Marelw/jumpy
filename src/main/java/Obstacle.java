public class Obstacle {

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

        if (Direction.equals("floor")) {
            y = -(int)(Math.random() * 100) - height / 2; //GAP PLACEMENT , Kan användas för att ändra svårighetsgrad senare
        }
    }

    public void update() {
        x -= speed;
    }
}
