
public class Bird extends Sprite {

    private final int gravity = 1;

    public Bird(int x, int y) {
        super("yellowbird-midflap.png", x, y);
    }

    public void flap() {
        setVelocity(0, -10);    // Jump upward
    }

    public void updateState() {
        velocityY += gravity;
        setVelocity(0, velocityY);
        update();
    }
}
