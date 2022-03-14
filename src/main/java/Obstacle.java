public class Obstacle {

    public String direction;
    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public Obstacle(String direction) {
        this.direction = direction;
        reset();
    }
    public void reset() {
        width = 66;
        height = 400;
        x = GameConstraints.PANEL_WIDTH + 2;

        if (direction.equals("floor")) {
            y = -(int)(Math.random() * 100) - height / 2; //GAP PLACEMENT , Kan användas för att ändra svårighetsgrad senare
            System.out.println(y);
        }
    }

    public void update() {
        x -= speed;
    }


    public boolean collides(int birdX, int birdY, int birdWidth, int birdHeight) {

        int margin = -7;

        if (inbetweenX(birdX - margin) || inbetweenX(birdX + birdWidth + margin)) {

            if(inbetweenY(birdY - margin) || inbetweenY(birdY + birdHeight + margin)) {
                return true;
            }
        }
        return false;
    }

    private boolean inbetween(int objectX, int mypos, int length) { //faktorisera mera
        return objectX > mypos && objectX < mypos + length;
    }

    private boolean inbetweenX(int objectX) {
        if (objectX > x && objectX < x + width) {
            return true;
        }
        return false;
    }

    private boolean inbetweenY(int objectY) {
        if (objectY > y && objectY < y + height) {
            return true;
        }
        return false;
    }
}
