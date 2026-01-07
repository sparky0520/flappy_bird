
public class Bird extends Sprite {

    private final int gravity = 1;

    public Bird(int x, int y) {
        super("yellowbird-midflap.png", x, y);
    }

    public void flap() {
        velocityY = -10; // Jump upward
    }

    public void updateState() {
        velocityY += gravity;
        update();
    }
}
